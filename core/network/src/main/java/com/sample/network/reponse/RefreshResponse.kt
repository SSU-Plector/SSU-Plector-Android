package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    @SerialName("accessToken")
    val accessToken: String?,

    @SerialName("refreshToken")
    val refreshToken: String?,
)
