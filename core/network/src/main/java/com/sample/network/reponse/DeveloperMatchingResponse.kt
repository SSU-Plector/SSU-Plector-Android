package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeveloperMatchingResponse(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("part1")
    val part1: String?,
    @SerialName("part2")
    val part2: String?,
    @SerialName("shortIntro")
    val shortIntro: String?,
)