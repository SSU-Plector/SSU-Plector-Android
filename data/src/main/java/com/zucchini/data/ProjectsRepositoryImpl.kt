package com.zucchini.data

import com.sample.network.service.ProjectsService
import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.domain.model.ProjectsListModel
import com.zucchini.domain.repository.ProjectsRepository
import com.zucchini.mapper.toProjectsDetailModel
import com.zucchini.mapper.toProjectsListModel
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsService: ProjectsService,
) : ProjectsRepository {
    override suspend fun getProjectsListData(): Result<ProjectsListModel> {
        return runCatching {
            projectsService.getProjectsListData(
                searchString = "",
                category = "SERVICE",
                sortType = "recent",
                page = 0,
            ).data.toProjectsListModel()
        }
    }

    override suspend fun getProjectsDetailData(
        projectId: Int,
    ): Result<ProjectsDetailModel> {
        return runCatching {
            projectsService.getProjectsDetailData(projectId).data.toProjectsDetailModel()
        }
    }
}
