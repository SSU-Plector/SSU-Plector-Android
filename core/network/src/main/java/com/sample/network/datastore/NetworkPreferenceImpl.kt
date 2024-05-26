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
        get() = preferences.getInt("user_id", -1)
        set(value) {
            preferences.edit(commit = true) {
                putInt("user_id", value)
            }
        }
    override var autoLoginConfigured: Boolean
        get() = preferences.getBoolean("auto_login", false)
        set(value) {
            preferences.edit(commit = true) {
                putBoolean("auto_login", value)
            }
        }

    override fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }
}
