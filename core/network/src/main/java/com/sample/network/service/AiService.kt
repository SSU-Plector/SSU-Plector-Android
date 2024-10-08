package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.DeveloperMatchingResponse
import com.sample.network.reponse.LoginResponse
import com.sample.network.reponse.MeetingProgressResponse
import com.sample.network.reponse.RefreshResponse
import com.sample.network.request.DeveloperMatchingRequest
import com.sample.network.request.MeetingProgressRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface AiService {

    @GET("/api/assistant/pm/meeting")
    suspend fun getMeetingProgress(
        @Query("introduceMyself") introduceMyself : Boolean,
        @Query("iceBreaking") iceBreaking : Boolean,
        @Query("brainstorming") brainstorming : Boolean,
        @Query("topicSelection") topicSelection : Boolean,
        @Query("progressSharing") progressSharing : Boolean,
        @Query("roleDivision") roleDivision : Boolean,
        @Query("troubleShooting") troubleShooting : Boolean,
        @Query("feedback") feedback : Boolean,
        @Query("time") time : Int?,
        @Query("numberOfParticipants") participants : Int?,
    ): BaseResponse<MeetingProgressResponse>

    @Multipart
    @POST("/api/assistant/pm/summary")
    suspend fun sendMeetingRecordFile(
        @Part file: MultipartBody.Part,
    ): BaseResponse<String>

    @GET("/api/assistant/designer/branding")
    suspend fun getBrandingData(
        @Query("projectInfo") brandingTextRequest : String?,
    ): BaseResponse<String>

    @GET("/api/assistant/designer/makeImage")
    suspend fun getProjectImageData(
        @Query("imageInfo") imageTextRequest : String?,
    ): BaseResponse<String>

    @GET("/api/developers/match")
    suspend fun getDevelopersMatching(
        @Query("part") part: String,
        @Query("languageList") languageList: List<String>,
        @Query("techStackList") techStackList: List<String>,
        @Query("projectExperience") projectExperience: Boolean,
        @Query("studentNumberMin") studentNumberMin: Int,
        @Query("studentNumberMax") studentNumberMax: Int,
        @Query("developerInfo") developerInfo: String?
    ): BaseResponse<List<DeveloperMatchingResponse>>
}
