package com.zucchini.data

import com.sample.network.service.AiService
import com.zucchini.domain.model.ai.DeveloperMatchingInfo
import com.zucchini.domain.model.ai.MatchingResult
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.ProgressMeetingInfo
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.domain.repository.AiRepository
import com.zucchini.mapper.toMatchingResult
import com.zucchini.mapper.toMeetingProgress
import com.zucchini.mapper.toMeetingProgressRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class AiRepositoryImpl
@Inject
constructor(
    private val authService: AiService,
) : AiRepository {
    override suspend fun getProgressMeetingData(
        setProgressMeeting: SetProgressMeeting?,
        progressMeetingInfo: ProgressMeetingInfo,
    ): Result<ProgressMeeting> =
        runCatching {
            authService
                .getMeetingProgress(
                    progressMeetingInfo.toMeetingProgressRequest(),
                    setProgressMeeting?.meetingTime,
                    setProgressMeeting?.participants,
                ).data
                .toMeetingProgress()
        }

    override suspend fun sendMeetingRecordFile(recordFile: String): Result<String> {
        val recordingFile = File(recordFile)
        val requestFile = recordingFile.asRequestBody("audio/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", recordingFile.name, requestFile)

        return runCatching {
            authService.sendMeetingRecordFile(body).data
        }
    }

    override suspend fun getProjectBranding(projectInfoTextRequest: String?): Result<String> =
        runCatching {
            authService.getBrandingData(projectInfoTextRequest).data
        }

    override suspend fun getProjectImage(projectImageRequest: String?): Result<String> =
        runCatching {
            authService.getProjectImageData(projectImageRequest).data
        }

    override suspend fun getDevelopersMatchingResult(
        matchingInfo: DeveloperMatchingInfo,
        developerInfo: String
    ): Result<List<MatchingResult>> =
        runCatching {
            authService.getDevelopersMatching(
                part = matchingInfo.part ?: "",
                languageList = matchingInfo.languageList,
                techStackList = matchingInfo.techStackList,
                projectExperience = matchingInfo.projectExperience,
                studentNumberMin = matchingInfo.studentNumberMin ?: 11,
                studentNumberMax = matchingInfo.studentNumberMax ?: 11,
                developerInfo = developerInfo,
            ).data.toMatchingResult()
        }
}
