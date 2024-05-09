package com.zucchini.mapper

import com.sample.network.reponse.DevelopersDetailResponse
import com.sample.network.reponse.ProjectsDetailResponse
import com.zucchini.domain.model.DeveloperInfoInDetailModel
import com.zucchini.domain.model.DeveloperListInProjectDetail
import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.domain.model.ProjectsDetailModel

internal fun ProjectsDetailResponse.toProjectsDetailModel(): ProjectsDetailModel {
    val developerListInProjectDetail = developerList.map { developerList ->
        DeveloperListInProjectDetail(
            id = developerList.id,
            name = developerList.name,
            partList = developerList.partList,
        )
    }

    return ProjectsDetailModel(
        id = id,
        name = name,
        imageLink = imageLink,
        developerList = developerListInProjectDetail,
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
