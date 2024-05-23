package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.ProjectsDetailResponse
import com.sample.network.reponse.ProjectsListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectsService {
    @GET("api/projects/list")
    suspend fun getProjectsListData(
        @Query("searchString") searchString: String?,
        @Query("category") category: String?,
        @Query("sortType") sortType: String,
        @Query("page") page: Int,
    ): BaseResponse<ProjectsListResponse>

    @GET("api/projects/{projectId}")
    suspend fun getProjectsDetailData(
        @Path("projectId") projectId: Int,
    ): BaseResponse<ProjectsDetailResponse>
}
