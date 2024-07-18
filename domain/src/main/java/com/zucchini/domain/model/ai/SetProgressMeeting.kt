package com.zucchini.domain.model.ai

data class SetProgressMeeting(
    val meetingTime: Int? = 3600000,
    val participants: Int? = 4,
    val progressMeetingInfo: ProgressMeetingInfo? = ProgressMeetingInfo(),
)

data class ProgressMeetingInfo(
    val introduceMyself: Boolean = false,
    val iceBreaking: Boolean = false,
    val brainstorming: Boolean = false,
    val topicSelection: Boolean = false,
    val progressSharing: Boolean = false,
    val roleDivision: Boolean = false,
    val troubleShooting: Boolean = false,
    val feedback: Boolean = false,
)
