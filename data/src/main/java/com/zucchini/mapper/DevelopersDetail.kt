package com.zucchini.mapper

import com.sample.network.reponse.DevelopersDetailResponse
import com.zucchini.domain.model.DeveloperInfoInDetailModel
import com.zucchini.domain.model.DevelopersDetailModel

internal fun DevelopersDetailResponse.toDevelopersDetailModel(): DevelopersDetailModel {
    val projectsListInDevelopersDetail = projectsListInDevelopersDetail.map { developerInfo ->
        DeveloperInfoInDetailModel(
            id = developerInfo.id,
            name = developerInfo.name,
            imageLink = developerInfo.imageLink,
            shortIntro = developerInfo.shortIntro,
            category = developerInfo.category,
            hits = developerInfo.hits,
        )
    }

    return DevelopersDetailModel(
        id = id,
        name = name,
        shortIntro = shortIntro,
        university = university,
        major = major,
        studentNumber = studentNumber,
        email = email,
        hits = hits,
        kakaoId = kakaoId,
        githubLink = githubLink,
        projectList = projectsListInDevelopersDetail,
        languageList = languageList,
        devToolList = devToolList,
        techStackList = techStackList,
        part1 = part1,
        part2 = part2,
        developer = developer,
    )
}
