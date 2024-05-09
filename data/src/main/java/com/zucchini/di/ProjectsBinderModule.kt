package com.zucchini.di

import com.zucchini.data.ProjectsRepositoryImpl
import com.zucchini.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProjectsBinderModule {
    @Binds
    @Singleton
    fun provideProjectsRepository(projectsRepository: ProjectsRepositoryImpl): ProjectsRepository
}
