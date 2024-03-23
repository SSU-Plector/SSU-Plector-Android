package com.zucchini.ssuplector

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SSUPlectorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
    }
}

private fun initTimber() {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
}
