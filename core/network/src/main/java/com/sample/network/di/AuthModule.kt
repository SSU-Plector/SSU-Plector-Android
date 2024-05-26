package com.sample.network.di

import com.hmh.hamyeonham.common.qualifier.Unsecured
import com.sample.network.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(@Unsecured retrofit: Retrofit): AuthService = retrofit.create()
}
