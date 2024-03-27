package com.zucchini.ssuplector

import com.zucchini.common.NavigationProvider
import com.zucchini.ssuplector.di.DefaultNavigationProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {
    @Binds
    @Singleton
    fun bindNavigator(navigator: DefaultNavigationProvider): NavigationProvider
}
