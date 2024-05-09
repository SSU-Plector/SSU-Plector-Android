package com.zucchini.domain.repository

import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.domain.model.DevelopersListModel

interface DevelopersRepository {
    suspend fun getDevelopersListData(): Result<DevelopersListModel>
    suspend fun getDevelopersDetailData(developerId: Int): Result<DevelopersDetailModel>
}
