package com.zucchini.domain.repository

import com.zucchini.domain.model.developers.DevelopersDetailModel
import com.zucchini.domain.model.developers.DevelopersListModel
import com.zucchini.domain.model.submit.SubmitDevInfo

interface DevelopersRepository {
    suspend fun getDevelopersListData(page: Int, part: String?): Result<DevelopersListModel>
    suspend fun getDevelopersDetailData(developerId: Int): Result<DevelopersDetailModel>

    suspend fun createDeveloperInfo(accessToken: String, email: String? = null, submitDevInfo: SubmitDevInfo): Result<Int>
}
