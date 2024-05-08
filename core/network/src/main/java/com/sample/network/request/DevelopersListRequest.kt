package com.sample.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DevelopersListRequest(
    @SerialName("part")
    val part: String,
    @SerialName("sortType")
    val sortType: String,
)
