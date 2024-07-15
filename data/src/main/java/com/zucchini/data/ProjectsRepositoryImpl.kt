package com.zucchini.data

import com.sample.network.service.ProjectsService
import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.domain.model.ProjectsListModel
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.domain.repository.ProjectsRepository
import com.zucchini.mapper.toProjectsDetailModel
import com.zucchini.mapper.toProjectsListModel
import com.zucchini.mapper.toSubmitProjectRequest
import okhttp3.MultipartBody
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

        override suspend fun submitProject(submitProjectInfo: SubmitProjectInfo, imagePath: MultipartBody.Part): Result<Int> =
            runCatching {
                projectsService
                    .submitProject(
                        submitProjectInfo.toSubmitProjectRequest(),
                        imagePath
                    ).data
            }
    }
