package com.zucchini.mapper

import com.sample.network.reponse.MeetingProgressResponse
import com.sample.network.request.MeetingProgressRequest
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.domain.model.ai.SetProgressMeeting.ProgressMeetingInfo

internal fun MeetingProgressResponse.toMeetingProgress(): ProgressMeeting {
    return ProgressMeeting(
        introduceMyself = introduceMyself,
        iceBreaking = iceBreaking,
        brainstorming = brainstorming,
        topicSelection = topicSelection,
        progressSharing = progressSharing,
        roleDivision = roleDivision,
        troubleShooting = troubleShooting,
        feedback = feedback,
    )
}

internal fun ProgressMeetingInfo.toMeetingProgressRequest(): MeetingProgressRequest {
    return MeetingProgressRequest(
        introduceMyself = introduceMyself,
        iceBreaking = iceBreaking,
        brainstorming = brainstorming,
        topicSelection = topicSelection,
        progressSharing = progressSharing,
        roleDivision = roleDivision,
        troubleShooting = troubleShooting,
        feedback = feedback,
    )
}
