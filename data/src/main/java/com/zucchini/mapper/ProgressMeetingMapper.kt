package com.zucchini.mapper

import com.sample.network.reponse.DeveloperMatchingResponse
import com.sample.network.reponse.MeetingProgressResponse
import com.sample.network.reponse.SearchDeveloperResultResponse
import com.sample.network.request.DeveloperMatchingRequest
import com.sample.network.request.MeetingProgressRequest
import com.zucchini.domain.model.FindDeveloperInfo
import com.zucchini.domain.model.ai.DeveloperMatchingInfo
import com.zucchini.domain.model.ai.MatchingResult
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.ProgressMeetingInfo

internal fun MeetingProgressResponse.toMeetingProgress(): ProgressMeeting =
    ProgressMeeting(
        introduceMyself = introduceMyself,
        iceBreaking = iceBreaking,
        brainstorming = brainstorming,
        topicSelection = topicSelection,
        progressSharing = progressSharing,
        roleDivision = roleDivision,
        troubleShooting = troubleShooting,
        feedback = feedback,
    )

internal fun ProgressMeetingInfo.toMeetingProgressRequest(): MeetingProgressRequest =
    MeetingProgressRequest(
        introduceMyself = introduceMyself,
        iceBreaking = iceBreaking,
        brainstorming = brainstorming,
        topicSelection = topicSelection,
        progressSharing = progressSharing,
        roleDivision = roleDivision,
        troubleShooting = troubleShooting,
        feedback = feedback ,
    )

internal fun List<DeveloperMatchingResponse>.toMatchingResult(): List<MatchingResult> =
    this.map { matchingResult ->
        MatchingResult(
            id = matchingResult.id ?: -1,
            name = matchingResult.name ?: "",
            email = matchingResult.email ?: "",
            part1 = matchingResult.part1 ?: "",
            part2 = matchingResult.part2 ?: "",
            description = matchingResult.shortIntro ?: "",
        )
    }