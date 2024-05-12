package com.zucchini.domain.model

data class ProjectsListModel(
    val currentElement: Int,
    val totalElement: Int,
    val totalPage: Int,
    val projectListInfoInList: List<ProjectListInfoInList>,
)

data class ProjectListInfoInList(
    val id: Int,
    val name: String,
    val imagePath: String?,
    val shortIntro: String,
    val category: String,
    val hits: Int,
)
