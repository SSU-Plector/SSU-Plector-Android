package com.zucchini.mapper

import com.sample.network.reponse.ProjectsListResponse
import com.zucchini.domain.model.projects.ProjectListInfoInList
import com.zucchini.domain.model.projects.ProjectsListModel

internal fun ProjectsListResponse.toProjectsListModel(): ProjectsListModel {
    val projectListInfoInList = projectResponseDtoList.map { dto ->
        ProjectListInfoInList(
            id = dto.id,
            name = dto.name,
            imagePath = dto.imagePath,
            shortIntro = dto.shortIntro,
            category = dto.category,
            hits = dto.hits,
        )
    }

    return ProjectsListModel(
        currentElement = currentElement,
        totalElement = totalElement,
        totalPage = totalPage,
        projectListInfoInList = projectListInfoInList,
    )
}
