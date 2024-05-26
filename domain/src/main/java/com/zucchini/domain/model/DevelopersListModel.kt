package com.zucchini.domain.model

data class DevelopersListModel(
    val currentElement: Int,
    val totalElement: Int,
    val totalPage: Int,
    val developerDetailList: List<DeveloperDetailInfoInListModel>,
)

data class DeveloperDetailInfoInListModel(
    val id: Int,
    val name: String?,
    val part1: String?,
    val part2: String?,
    val githubLink: String?,
    val imageLink: String?,
    val hits: Int,
)
