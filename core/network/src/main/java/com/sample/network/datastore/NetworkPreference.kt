package com.sample.network.datastore

interface NetworkPreference {
    var accessToken: String
    var refreshToken: String
    var developerId: Int
    var kakaoEmail: String
    var kakaoNickname: String
    var kakaoProfileImage: String
    var autoLoginConfigured: Boolean
    fun clear()
}
