package com.sample.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateDevelopersRequest(
    @SerialName("shortIntro")
    val shortIntro: String,
    @SerialName("university")
    val university: String,
    @SerialName("major")
    val major: String,
    @SerialName("studentNumber")
    val studentNumber: String,
    @SerialName("kakaoId")
    val kakaoId: String,
    @SerialName("githubLink")
    val githubLink: String,
    @SerialName("part1")
    val part1: String,
    @SerialName("part2")
    val part2: String,
    @SerialName("languageList")
    val languageList: List<String>,
    @SerialName("devToolList")
    val devToolList: List<String>,
    @SerialName("techStackList")
    val techStackList: List<String>,

)
