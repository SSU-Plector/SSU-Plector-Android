package com.sample.network.datastore

interface NetworkPreference {
    var accessToken: String
    var refreshToken: String
    var developerId: Int
    var autoLoginConfigured: Boolean
    fun clear()
}
