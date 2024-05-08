package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsDetailResponse(
    @SerialName("devToolList")
    val devToolList: List<String>,
    @SerialName("developer")
    val developer: Boolean,
    @SerialName("email")
    val email: String,
    @SerialName("githubLink")
    val githubLink: String,
    @SerialName("hits")
    val hits: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("imageLink")
    val imageLink: String,
    @SerialName("kakaoId")
    val kakaoId: String,
    @SerialName("languageList")
    val languageList: List<String>,
    @SerialName("linkList")
    val linkList: List<String>,
    @SerialName("major")
    val major: String,
    @SerialName("name")
    val name: String,
    @SerialName("part1")
    val part1: String,
    @SerialName("part2")
    val part2: String,
    @SerialName("projectList")
    val projectList: List<Project>,
    @SerialName("shortIntro")
    val shortIntro: String,
    @SerialName("studentNumber")
    val studentNumber: String,
    @SerialName("techStackList")
    val techStackList: List<String>,
    @SerialName("university")
    val university: String,
) {
    @Serializable
    data class Project(
        @SerialName("category")
        val category: String,
        @SerialName("hits")
        val hits: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("imageLink")
        val imageLink: String,
        @SerialName("name")
        val name: String,
        @SerialName("shortIntro")
        val shortIntro: String,
    )
}
