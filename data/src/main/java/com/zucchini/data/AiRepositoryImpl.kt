package com.zucchini.data

import com.sample.network.service.AiService
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.domain.repository.AiRepository
import com.zucchini.mapper.toMeetingProgress
import com.zucchini.mapper.toMeetingProgressRequest
import javax.inject.Inject

class AiRepositoryImpl
    @Inject
    constructor(
        private val authService: AiService,
    ) : AiRepository {
        override suspend fun getProgressMeetingData(setProgressMeeting: SetProgressMeeting): Result<ProgressMeeting> =
            runCatching {
                authService
                    .getMeetingProgress(
                        setProgressMeeting.progressMeetingInfo.toMeetingProgressRequest(),
                        setProgressMeeting.meetingTime,
                        setProgressMeeting.participants,
                    ).data
                    .toMeetingProgress()
            }
    }
