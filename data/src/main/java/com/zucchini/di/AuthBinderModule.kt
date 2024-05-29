package com.zucchini.di

import com.zucchini.data.AuthRepositoryImpl
import com.zucchini.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract interface AuthBinderModule {
    @Binds
    @Singleton
    abstract fun provideUsageGoalsRepository(loginRepository: AuthRepositoryImpl): AuthRepository
}