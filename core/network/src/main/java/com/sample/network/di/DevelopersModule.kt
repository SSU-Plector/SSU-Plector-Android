package com.sample.network.di

import com.hmh.hamyeonham.common.qualifier.Unsecured
import com.sample.network.service.DevelopersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DevelopersModule {
    @Provides
    @Singleton
    fun provideDevelopersApi(@Unsecured retrofit: Retrofit): DevelopersService = retrofit.create()
}
