package com.zucchini.ssuplector

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SSUPlectorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
