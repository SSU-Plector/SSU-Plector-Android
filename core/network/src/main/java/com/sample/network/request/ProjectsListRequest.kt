package com.sample.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsListRequest(
    @SerialName("searchString")
    val searchString: String,
    @SerialName("category")
    val category: String,
    @SerialName("sortType")
    val sortType: String,
)
