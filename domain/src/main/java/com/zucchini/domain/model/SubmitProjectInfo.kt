package com.zucchini.domain.model

data class SubmitProjectInfo(
    val projectName: String = "",
    val projectGithub: String = "",
    val imagePath: String = "",
    val projectShortIntro: String = "",
    val projectLongIntro: String = "",
    val projectCategoryList: List<String> = emptyList(),
    val projectTechStackList: List<String> = emptyList(),
    val projectLanguageList: List<String> = emptyList(),
    val projectCooperationList: List<String> = emptyList(),
    val projectWebLink: String = "",
    val projectAppLink: String = "",
    val projectLink: String = "",
    var projectDeveloperList: List<Int> = emptyList(),
)