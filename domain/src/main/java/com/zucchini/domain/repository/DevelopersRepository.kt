package com.zucchini.domain.repository

import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.domain.model.DevelopersListModel
import com.zucchini.domain.model.SubmitDevInfo

interface DevelopersRepository {
    suspend fun getDevelopersListData(page: Int, part: String?): Result<DevelopersListModel>
    suspend fun getDevelopersDetailData(developerId: Int): Result<DevelopersDetailModel>

    suspend fun createDeveloperInfo(accessToken: String, email: String? = null, submitDevInfo: SubmitDevInfo): Result<Int>
}
