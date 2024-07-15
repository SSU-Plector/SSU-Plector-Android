package com.zucchini.domain.repository

import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.domain.model.ProjectsListModel
import com.zucchini.domain.model.SubmitProjectInfo

interface ProjectsRepository {
    suspend fun getProjectsListData(
        searchString: String?,
        category: String?,
        sortType: String,
        page: Int,
    ): Result<ProjectsListModel>

    suspend fun getProjectsDetailData(projectId: Int): Result<ProjectsDetailModel>

    suspend fun submitProject(submitProjectInfo: SubmitProjectInfo): Result<Int>
}
