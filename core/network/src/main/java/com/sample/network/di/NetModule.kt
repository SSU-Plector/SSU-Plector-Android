package com.sample.network.di

import com.hmh.hamyeonham.common.qualifier.Auth
import com.hmh.hamyeonham.common.qualifier.Log
import com.hmh.hamyeonham.common.qualifier.Secured
import com.hmh.hamyeonham.common.qualifier.Unsecured
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sample.network.AuthInterceptor
import com.sample.network.SPAuthenticator
import com.zucchini.core.network.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val baseUrl = BuildConfig.BASE_URL

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun provideJsonConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    @Log
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            },
        )

    @Singleton
    @Provides
    @Auth
    fun provideAuthInterceptor(interceptor: AuthInterceptor): Interceptor = interceptor

    @Singleton
    @Provides
    @Secured
    fun provideOkHttpClient(
        @Log logInterceptor: Interceptor,
        @Auth authInterceptor: Interceptor,
        authenticator: Authenticator,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(authenticator)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    @Unsecured
    fun provideOkHttpClientNotNeededAuth(
        @Log logInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    @Secured
    fun provideRetrofit(
        @Secured client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    @Unsecured
    fun provideRetrofitNotNeededAuth(
        @Unsecured client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideAuthenticator(authenticator: SPAuthenticator): Authenticator
    }
}
