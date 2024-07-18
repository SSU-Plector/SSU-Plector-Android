package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.LoginResponse
import com.sample.network.reponse.MeetingProgressResponse
import com.sample.network.reponse.RefreshResponse
import com.sample.network.request.MeetingProgressRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AiService {

    @GET("/api/assistant/pm/meeting")
    suspend fun getMeetingProgress(
        @Query("pmRequestDTO") setMeetingProgress: MeetingProgressRequest,
        @Query("time") time : Int,
        @Query("numberOfParticipants") participants : Int,
    ): BaseResponse<MeetingProgressResponse>
}
