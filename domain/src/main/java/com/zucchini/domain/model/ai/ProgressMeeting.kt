package com.zucchini.domain.model.ai

data class ProgressMeeting(
    val introduceMyself: Int? = 600000,
    val iceBreaking: Int? = 600000,
    val brainstorming: Int? = 600000,
    val topicSelection: Int? = 600000,
    val progressSharing: Int? = 600000,
    val roleDivision: Int? = 600000,
    val troubleShooting: Int? = 600000,
    val feedback: Int? = 600000,
)
