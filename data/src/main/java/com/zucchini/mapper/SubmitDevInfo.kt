package com.zucchini.mapper

import com.sample.network.request.CreateDevelopersRequest
import com.zucchini.domain.model.SubmitDevInfo

internal fun SubmitDevInfo.toCreateDevelopersRequest(): CreateDevelopersRequest {
    return CreateDevelopersRequest(
        shortIntro = devIntro,
        university = devUniversity,
        major = devMajor,
        studentNumber = devStudentNumber,
        kakaoId = "",
        githubLink = devGithub,
        part1 = devPart1,
        part2 = devPart2,
        languageList = devLanguageList,
        devToolList = devCooperationList,
        techStackList = devTechStackList,
    )
}
