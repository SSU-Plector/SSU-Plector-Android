package com.zucchini.domain.model

data class ProjectsDetailModel(
    val id: Int,
    val name: String,
    val imageLink: String,
    val developerList: List<DeveloperListInProjectDetail>,
    val shortIntro: String,
    val longIntro: String,
    val category: String,
    val hits: Int,
    val githubLink: String,
    val infoPageLink: String,
    val webLink: String,
    val appLink: String,
    val languageList: List<String>,
    val devToolList: List<String>,
    val techStackList: List<String>,
)

data class DeveloperListInProjectDetail(
    val id: Int,
    val name: String,
    val partList: List<String>,
)
