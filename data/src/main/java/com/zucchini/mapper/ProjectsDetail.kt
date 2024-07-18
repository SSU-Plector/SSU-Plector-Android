package com.zucchini.mapper

import com.sample.network.reponse.ProjectsDetailResponse
import com.zucchini.domain.model.projects.ProjectsDetailModel

internal fun ProjectsDetailResponse.toProjectsDetailModel(): ProjectsDetailModel {
    val developerListInProjectDetail = developerList?.map { developerList ->
        ProjectsDetailModel.DeveloperListInProjectDetail(
            id = developerList.id,
            name = developerList.name,
            partList = developerList.partList,
        )
    }

    return ProjectsDetailModel(
        id = id,
        name = name,
        imageLink = imageLink,
        developerList = developerListInProjectDetail ?: emptyList(),
        shortIntro = shortIntro,
        longIntro = longIntro,
        category = category,
        hits = hits,
        githubLink = githubLink,
        infoPageLink = infoPageLink,
        webLink = webLink,
        appLink = appLink,
        languageList = languageList,
        devToolList = devToolList,
        techStackList = techStackList,
    )
}
