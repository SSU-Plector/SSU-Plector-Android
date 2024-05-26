package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    @SerialName("accessToken")
    val developerName: String?,

    @SerialName("refreshToken")
    val developerEmail: String?,
)
