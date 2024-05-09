package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("imageLink")
    val imageLink: String,
    @SerialName("developerList")
    val developerList: List<DeveloperListInProjectDetail>,
    @SerialName("shortIntro")
    val shortIntro: String,
    @SerialName("longIntro")
    val longIntro: String,
    @SerialName("category")
    val category: String,
    @SerialName("hits")
    val hits: Int,
    @SerialName("githubLink")
    val githubLink: String,
    @SerialName("infoPageLink")
    val infoPageLink: String,
    @SerialName("webLink")
    val webLink: String,
    @SerialName("appLink")
    val appLink: String,
    @SerialName("languageList")
    val languageList: List<String>,
    @SerialName("devToolList")
    val devToolList: List<String>,
    @SerialName("techStackList")
    val techStackList: List<String>,
) {
    @Serializable
    data class DeveloperListInProjectDetail(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("partList")
        val partList: List<String>,
    )
}
