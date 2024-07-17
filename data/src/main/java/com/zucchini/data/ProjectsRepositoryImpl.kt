package com.zucchini.data

import android.util.Log
import com.sample.network.service.ProjectsService
import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.domain.model.ProjectsListModel
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.domain.repository.ProjectsRepository
import com.zucchini.mapper.toProjectsDetailModel
import com.zucchini.mapper.toProjectsListModel
import com.zucchini.mapper.toSubmitProjectRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
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

    override suspend fun submitProject(submitProjectInfo: SubmitProjectInfo, imagePath: String): Result<Int> =
        runCatching {
            var uploadImageFile: MultipartBody.Part = MultipartBody.Part.createFormData("image", "")

            val file = File(imagePath)
            if (file.exists()) {
                val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                Log.e("ProjectsRepositoryImpl Exists", "requestFile: ${requestFile}")
                uploadImageFile =  MultipartBody.Part.createFormData("image", file.name, requestFile)
            } else {
                Log.e("prepareFilePart", "File does not exist: $imagePath")
            }

            Log.e("ProjectsRepositoryImpl", "uploadImageFile: ${file}, ${imagePath}, ${uploadImageFile}")

            projectsService
                .submitProject(
                    submitProjectInfo.toSubmitProjectRequest(),
                    uploadImageFile
                ).data
        }
    }
