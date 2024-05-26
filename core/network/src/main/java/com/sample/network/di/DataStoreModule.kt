package com.sample.network.di

import com.sample.network.datastore.NetworkPreference
import com.sample.network.datastore.NetworkPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Singleton
        @Binds
        fun bindAppPreferences(dataStore: NetworkPreferenceImpl): NetworkPreference
    }
}
