package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.LoginResponse
import com.sample.network.reponse.MeetingProgressResponse
import com.sample.network.reponse.RefreshResponse
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
        @Query("pmRequestDTO") setMeetingProgress: MeetingProgressRequest,
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
}
