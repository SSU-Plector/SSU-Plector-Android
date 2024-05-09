package com.zucchini.di

import com.zucchini.data.DevelopersRepositoryImpl
import com.zucchini.domain.repository.DevelopersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DevelopersBinderModule {
    @Binds
    @Singleton
    fun provideDevelopersRepository(developersRepository: DevelopersRepositoryImpl): DevelopersRepository
}
