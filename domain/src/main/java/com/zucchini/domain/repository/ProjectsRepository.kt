package com.zucchini.domain.repository

import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.domain.model.projects.ProjectsDetailModel
import com.zucchini.domain.model.projects.ProjectsListModel

interface ProjectsRepository {
    suspend fun getProjectsListData(
        searchString: String?,
        category: String?,
        sortType: String,
        page: Int,
    ): Result<ProjectsListModel>

    suspend fun getProjectsDetailData(projectId: Int): Result<ProjectsDetailModel>

    suspend fun submitProject(
        submitProjectInfo: SubmitProjectInfo,
        imagePath: String,
    ): Result<Int>
}
