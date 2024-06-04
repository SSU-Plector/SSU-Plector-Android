package com.sample.network.datastore

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkPreferenceImpl @Inject constructor(
    private val preferences: SharedPreferences,
) : NetworkPreference {

    override var accessToken: String
        get() = preferences.getString("access_token", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("access_token", value)
            }
        }
    override var refreshToken: String
        get() = preferences.getString("refresh_token", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("refresh_token", value)
            }
        }
    override var developerId: Int
        get() = preferences.getInt("developer_id", -1)
        set(value) {
            preferences.edit(commit = true) {
                putInt("developer_id", value)
            }
        }
    override var autoLoginConfigured: Boolean
        get() = preferences.getBoolean("auto_login", false)
        set(value) {
            preferences.edit(commit = true) {
                putBoolean("auto_login", value)
            }
        }

    override var kakaoEmail: String
        get() = preferences.getString("kakao_email", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("kakao_email", value)
            }
        }

    override var kakaoNickname: String
        get() = preferences.getString("kakao_nickname", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("kakao_nickname", value)
            }
        }
    override var kakaoProfileImage: String
        get() = preferences.getString("kakao_profile_image", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("kakao_profile_image", value)
            }
        }

    override fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }
}
