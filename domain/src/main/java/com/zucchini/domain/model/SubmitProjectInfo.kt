package com.zucchini.domain.model

data class SubmitProjectInfo(
    var projectName: String = "",
    var projectGithub: String = "",
    var imagePath: String = "",
    var projectShortIntro: String = "",
    var projectLongIntro: String = "",
    var projectCategoryList: List<String> = emptyList(),
    var projectTechStackList: List<String> = emptyList(),
    var projectLanguageList: List<String> = emptyList(),
    var projectCooperationList: List<String> = emptyList(),
    var projectWebLink: String = "",
    var projectAppLink: String = "",
    var projectLink: String = "",
    var projectDeveloperList: List<Int> = emptyList(),
)