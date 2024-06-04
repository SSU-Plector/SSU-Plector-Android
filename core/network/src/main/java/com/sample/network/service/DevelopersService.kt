package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.DevelopersDetailResponse
import com.sample.network.reponse.DevelopersListResponse
import com.sample.network.request.CreateDevelopersRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface DevelopersService {

    @GET("/api/developers/list")
    suspend fun getDevelopersListData(
        @Query("sortType") sortType: String? = null,
        @Query("part") part: String? = null,
        @Query("page") page: Int = 0,
    ): BaseResponse<DevelopersListResponse>

    @GET("/api/developers/{developerId}")
    suspend fun getDevelopersDetailData(
        @Path("developerId") developerId: Int,
    ): BaseResponse<DevelopersDetailResponse>

    @PATCH("/api/developers")
    suspend fun createDeveloperInfo(
        @Header("Authorization") accessToken: String,
        @Query("email") email: String? = null,
        @Body request: CreateDevelopersRequest,
    ): BaseResponse<Int>
}
