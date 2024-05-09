package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.DevelopersDetailResponse
import com.sample.network.reponse.DevelopersListResponse
import retrofit2.http.GET
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
        developerId: Int,
    ): BaseResponse<DevelopersDetailResponse>
}
