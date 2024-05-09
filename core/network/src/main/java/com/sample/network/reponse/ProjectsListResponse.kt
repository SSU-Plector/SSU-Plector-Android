package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsListResponse(
    @SerialName("currentElement")
    val currentElement: Int,
    @SerialName("totalPage")
    val totalPage: Int,
    @SerialName("totalElement")
    val totalElement: Int,
    @SerialName("projectResponseDtoList")
    val projectResponseDtoList: List<ProjectListInfoInList>,
) {
    @Serializable
    data class ProjectListInfoInList(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("imagePath")
        val imagePath: String,
        @SerialName("shortIntro")
        val shortIntro: String,
        @SerialName("category")
        val category: String,
        @SerialName("hits")
        val hits: Int,
    )
}
