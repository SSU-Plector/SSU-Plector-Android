package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("developerId")
    val developerId: Int?,

    @SerialName("accessToken")
    val accessToken: String?,

    @SerialName("refreshToken")
    val refreshToken: String?,

    @SerialName("isLogin")
    val isLogin: Boolean,
)
