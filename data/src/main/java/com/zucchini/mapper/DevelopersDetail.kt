package com.zucchini.mapper

import com.sample.network.reponse.DevelopersDetailResponse
import com.zucchini.domain.model.DeveloperInfoInDetailModel
import com.zucchini.domain.model.DevelopersDetailModel

internal fun DevelopersDetailResponse.toDevelopersDetailModel(): DevelopersDetailModel {
    val developerList = developerList.map { developerInfo ->
        DeveloperInfoInDetailModel(
            id = developerInfo.id,
            name = developerInfo.name,
            partList = developerInfo.partList,
        )
    }

    return DevelopersDetailModel(
        id = id,
        name = name,
        imageLink = imageLink,
        developerList = developerList,
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
