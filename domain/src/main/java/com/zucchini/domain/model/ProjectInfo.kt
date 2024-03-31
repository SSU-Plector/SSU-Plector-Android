package com.zucchini.domain.model

data class ProjectInfo(
    val image: Int? = null,
    val name: String = "",
    val description: String = "",
    val sorted: String = "",
    val clicked: Int = 0,
)
