package com.zucchini.di

import com.zucchini.data.AiRepositoryImpl
import com.zucchini.domain.repository.AiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract interface AiBinderModule {
    @Binds
    @Singleton
    abstract fun provideAiRepository(aiRepository: AiRepositoryImpl): AiRepository
}
