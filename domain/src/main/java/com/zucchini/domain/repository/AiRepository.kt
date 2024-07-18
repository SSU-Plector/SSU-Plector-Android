package com.zucchini.domain.repository

import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.SetProgressMeeting

interface AiRepository {
    suspend fun getProgressMeetingData(setProgressMeeting: SetProgressMeeting): Result<ProgressMeeting>
}
