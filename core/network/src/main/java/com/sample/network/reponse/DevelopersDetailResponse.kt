package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DevelopersDetailResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("shortIntro")
    val shortIntro: String,
    @SerialName("university")
    val university: String,
    @SerialName("major")
    val major: String,
    @SerialName("studentNumber")
    val studentNumber: String,
    @SerialName("email")
    val email: String,
    @SerialName("hits")
    val hits: Int,
    @SerialName("kakaoId")
    val kakaoId: String,
    @SerialName("githubLink")
    val githubLink: String,
    @SerialName("imageLink")
    val imageLink: String,
    @SerialName("projectList")
    val projectsListInDevelopersDetail: List<ProjectsListInDevelopersDetail>,
    @SerialName("languageList")
    val languageList: List<String>,
    @SerialName("devToolList")
    val devToolList: List<String>,
    @SerialName("techStackList")
    val techStackList: List<String>,
    @SerialName("part1")
    val part1: String?,
    @SerialName("part2")
    val part2: String?,
    @SerialName("developer")
    val developer: Boolean,
) {
    @Serializable
    data class ProjectsListInDevelopersDetail(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("imageLink")
        val imageLink: String,
        @SerialName("shortIntro")
        val shortIntro: String,
        @SerialName("category")
        val category: String,
        @SerialName("hits")
        val hits: Int,
    )
}
