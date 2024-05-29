package com.zucchini.domain.model.auth

data class Login(
    val developerId: Int,
    val accessToken: String,
    val refreshToken: String,
    val isLogin: Boolean
)
