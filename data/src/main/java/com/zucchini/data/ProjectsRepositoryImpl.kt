package com.zucchini.data

import com.google.gson.Gson
import com.sample.network.service.ProjectsService
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.domain.model.projects.ProjectsDetailModel
import com.zucchini.domain.model.projects.ProjectsListModel
import com.zucchini.domain.repository.ProjectsRepository
import com.zucchini.mapper.toProjectsDetailModel
import com.zucchini.mapper.toProjectsListModel
import com.zucchini.mapper.toSubmitProjectRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ProjectsRepositoryImpl
@Inject
constructor(
    private val projectsService: ProjectsService,
) : ProjectsRepository {
    override suspend fun getProjectsListData(
        searchString: String?,
        category: String?,
        sortType: String,
        page: Int,
    ): Result<ProjectsListModel> =
        runCatching {
            projectsService
                .getProjectsListData(
                    searchString = searchString,
                    category = category,
                    sortType = sortType,
                    page = page,
                ).data
                .toProjectsListModel()
        }

    override suspend fun getProjectsDetailData(projectId: Int): Result<ProjectsDetailModel> =
        runCatching {
            projectsService.getProjectsDetailData(projectId).data.toProjectsDetailModel()
        }

    override suspend fun submitProject(
        submitProjectInfo: SubmitProjectInfo,
        imagePath: String,
    ): Result<Int> =
        runCatching {
            // JSON 데이터를 문자열로 변환
            val gson = Gson()
            val json = gson.toJson(submitProjectInfo.toSubmitProjectRequest())
            val jsonRequestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

            // JSON requestBody를 MultipartBody.Part로 변환
            val jsonPart = MultipartBody.Part.createFormData("requestDTO", null, jsonRequestBody)

            // 파일을 MultipartBody.Part로 변환
            val file = File(imagePath)
            val fileRequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)

            // 서비스 호출
            projectsService.submitProject(jsonPart, imagePart).data
        }
}
