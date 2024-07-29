package com.sample.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingProgressResponse(
    @SerialName("introduceMyself")
    val introduceMyself: Int?,
    @SerialName("iceBreaking")
    val iceBreaking: Int?,
    @SerialName("brainstorming")
    val brainstorming: Int?,
    @SerialName("topicSelection")
    val topicSelection: Int,
    @SerialName("progressSharing")
    val progressSharing: Int?,
    @SerialName("roleDivision")
    val roleDivision: Int?,
    @SerialName("troubleShooting")
    val troubleShooting: Int?,
    @SerialName("feedback")
    val feedback: Int,
)