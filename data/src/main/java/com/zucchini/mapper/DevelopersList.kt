package com.zucchini.mapper

import com.sample.network.reponse.DevelopersListResponse
import com.zucchini.domain.model.DeveloperDetailInfoInListModel
import com.zucchini.domain.model.DevelopersListModel

internal fun DevelopersListResponse.toDevelopersListModel(): DevelopersListModel {
    val developerDetailListInList = developerResponseDTOListInList.map { dto ->
        DeveloperDetailInfoInListModel(
            id = dto.id,
            name = dto.name,
            part1 = dto.part1,
            part2 = dto.part2,
            githubLink = dto.githubLink,
            hits = dto.hits,
        )
    }

    return DevelopersListModel(
        currentElement = currentElement,
        totalElement = totalElement,
        totalPage = totalPage,
        developerDetailList = developerDetailListInList,
    )
}
