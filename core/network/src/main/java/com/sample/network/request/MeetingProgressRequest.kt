package com.sample.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeetingProgressRequest(
    @SerialName("introduceMyself")
    val introduceMyself: Boolean,
    @SerialName("iceBreaking")
    val iceBreaking: Boolean,
    @SerialName("brainstorming")
    val brainstorming: Boolean,
    @SerialName("topicSelection")
    val topicSelection: Boolean,
    @SerialName("progressSharing")
    val progressSharing: Boolean,
    @SerialName("roleDivision")
    val roleDivision: Boolean,
    @SerialName("troubleShooting")
    val troubleShooting: Boolean,
    @SerialName("feedback")
    val feedback: Boolean,
)
