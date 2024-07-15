package com.zucchini.mapper

import com.sample.network.reponse.SearchDeveloperResultResponse
import com.zucchini.domain.model.FindDeveloperInfo

internal fun List<SearchDeveloperResultResponse>.toSearchDeveloperResult(): List<FindDeveloperInfo> =
    this.map { developer ->
        FindDeveloperInfo(
            developerName = developer.name ?: "",
            developerEmail = developer.email ?: "",
            developerId = developer.id ?: -1,
        )
    }