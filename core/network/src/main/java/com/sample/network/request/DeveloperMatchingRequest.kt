package com.sample.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeveloperMatchingRequest(
    @SerialName("part")
    val part: String?,
    @SerialName("languageList")
    val languageList: List<String>,
    @SerialName("techStackList")
    val techStackList: List<String>,
    @SerialName("projectExperience")
    val projectExperience: Boolean,
    @SerialName("studentNumberMin")
    val studentNumberMin: Int?,
    @SerialName("studentNumberMax")
    val studentNumberMax: Int?,
)
