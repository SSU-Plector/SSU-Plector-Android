package com.zucchini.domain.repository

import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.ProgressMeetingInfo
import com.zucchini.domain.model.ai.SetProgressMeeting

interface AiRepository {
    suspend fun getProgressMeetingData(setProgressMeeting: SetProgressMeeting?, progressMeetingInfo: ProgressMeetingInfo): Result<ProgressMeeting>
    suspend fun sendMeetingRecordFile(recordFile: String) : Result<String>
    suspend fun getProjectBranding(projectInfoTextRequest: String?) : Result<String>
    suspend fun getProjectImage(projectImageRequest: String?) : Result<String>

}
