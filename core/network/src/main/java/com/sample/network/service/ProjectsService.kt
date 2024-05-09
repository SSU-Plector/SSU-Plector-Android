package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.ProjectsDetailResponse
import com.sample.network.reponse.ProjectsListReponse
import com.sample.network.request.ProjectsListRequest
import retrofit2.http.Body
import retrofit2.http.GET

interface ProjectsService {
    @GET("api/projects/list")
    suspend fun getProjectsListData(@Body request: ProjectsListRequest): BaseResponse<ProjectsListReponse>

    @GET("api/projects/{projectId}")
    suspend fun getProjectsDetailData(
        projectId: Int,
    ): BaseResponse<ProjectsDetailResponse>
}
