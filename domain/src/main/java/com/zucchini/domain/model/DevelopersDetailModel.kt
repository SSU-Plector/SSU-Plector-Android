package com.zucchini.domain.model

data class DevelopersDetailModel(
    val id: Int,
    val name: String?,
    val shortIntro: String?,
    val university: String?,
    val major: String?,
    val studentNumber: String?,
    val email: String?,
    val hits: Int?,
    val kakaoId: String?,
    val githubLink: String?,
    val imageLink: String?,
    val projectList: List<ProjectInfoInDevDetailModel>?,
    val languageList: List<String?>?,
    val devToolList: List<String?>?,
    val techStackList: List<String?>?,
    val part1: String?,
    val part2: String?,
    val developer: Boolean?,
)

data class ProjectInfoInDevDetailModel(
    val id: Int,
    val name: String?,
    val imageLink: String?,
    val shortIntro: String?,
    val category: String?,
)
