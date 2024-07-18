package com.zucchini.data

import android.util.Log
import com.sample.network.service.AiService
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.ProgressMeetingInfo
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

        override suspend fun getProgressMeetingData(
            setProgressMeeting: SetProgressMeeting?,
            progressMeetingInfo: ProgressMeetingInfo,
        ): Result<ProgressMeeting> {
            return runCatching {
                authService
                    .getMeetingProgress(
                        progressMeetingInfo.toMeetingProgressRequest(),
                        setProgressMeeting?.meetingTime,
                        setProgressMeeting?.participants,
                    ).data
                    .toMeetingProgress()
            }
            Log.d("AiRepositoryImpl", "getProgressMeetingData: ${progressMeetingInfo.toMeetingProgressRequest()}")
        }
    }
