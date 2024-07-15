package com.sample.network.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubmitProjectRequest(
    @SerialName("appLink")
    val appLink: String,
    @SerialName("category")
    val category: String,
    @SerialName("devToolList")
    val devToolList: List<String>,
    @SerialName("githubLink")
    val githubLink: String,
    @SerialName("infoPageLink")
    val infoPageLink: String,
    @SerialName("languageList")
    val languageList: List<String>,
    @SerialName("longIntro")
    val longIntro: String,
    @SerialName("name")
    val name: String,
    @SerialName("shortIntro")
    val shortIntro: String,
    @SerialName("techStackList")
    val techStackList: List<String>,
    @SerialName("webLink")
    val webLink: String,
    @SerialName("projectDevloperList")
    val projectDeveloperList: List<Int>
)