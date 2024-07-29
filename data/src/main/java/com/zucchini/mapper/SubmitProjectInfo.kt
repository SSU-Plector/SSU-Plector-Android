package com.zucchini.mapper

import com.sample.network.request.SubmitProjectRequest
import com.zucchini.domain.model.SubmitProjectInfo


internal fun SubmitProjectInfo.toSubmitProjectRequest(): SubmitProjectRequest {
    return SubmitProjectRequest(
        name = projectName,
        shortIntro = projectShortIntro,
        longIntro = projectLongIntro,
        githubLink = projectGithub,
        webLink = projectWebLink,
        infoPageLink = projectLink,
        appLink = projectAppLink,
        category = projectCategoryList.get(0),
        languageList = projectLanguageList,
        devToolList = projectCooperationList,
        techStackList = projectTechStackList,
        projectDevloperList = projectDeveloperList
    )
}

