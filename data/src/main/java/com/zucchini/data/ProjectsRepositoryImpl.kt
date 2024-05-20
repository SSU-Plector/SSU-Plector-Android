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
    override suspend fun getProjectsListData(searchString: String, category: String, sortType: String, page: Int): Result<ProjectsListModel> {
        return runCatching {
            projectsService.getProjectsListData(
                searchString = searchString,
                category = category, // TODO : 디폴트값 어떻게 할지?
                sortType = sortType,
                page = page,
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
