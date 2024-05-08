package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DevlopersListReponse(
    @SerialName("currentElement")
    val currentElement: Int,
    @SerialName("totalElement")
    val totalElement: Int,
    @SerialName("totalPage")
    val totalPage: Int,
    @SerialName("developerResponseDTOList")
    val developerResponseDTOList: List<DeveloperDetailInfo>,
) {
    @Serializable
    data class DeveloperDetailInfo(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("part1")
        val part1: String,
        @SerialName("part2")
        val part2: String,
        @SerialName("githubLink")
        val githubLink: String,
        @SerialName("hits")
        val hits: Int,
    )
}

