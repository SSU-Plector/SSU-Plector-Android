package com.zucchini.domain.model.ai

data class DeveloperMatchingInfo(
    val part: String?,
    val languageList: List<String>,
    val techStackList: List<String>,
    val projectExperience: Boolean,
    val studentNumberMin: Int?,
    val studentNumberMax: Int?,
)

data class DeveloperInfo(
    val developerAdditionalCondition: String
)