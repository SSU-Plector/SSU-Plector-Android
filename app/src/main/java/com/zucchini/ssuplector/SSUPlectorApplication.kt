package com.zucchini.ssuplector

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.zucchini.ssuplector.BuildConfig.KAKAO_NATIVE_APP_KEY
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SSUPlectorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, KAKAO_NATIVE_APP_KEY)
        initTimber()
        // kakao keyhash
        // Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")
    }
}

private fun initTimber() {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
}
