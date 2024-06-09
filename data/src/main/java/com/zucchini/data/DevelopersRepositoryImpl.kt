package com.zucchini.data

import com.sample.network.service.DevelopersService
import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.domain.model.DevelopersListModel
import com.zucchini.domain.model.SubmitDevInfo
import com.zucchini.domain.repository.DevelopersRepository
import com.zucchini.mapper.toCreateDevelopersRequest
import com.zucchini.mapper.toDevelopersDetailModel
import com.zucchini.mapper.toDevelopersListModel
import javax.inject.Inject

class DevelopersRepositoryImpl @Inject constructor(
    private val developersService: DevelopersService,
) : DevelopersRepository {
    override suspend fun getDevelopersListData(
        page: Int,
        part: String?,
    ): Result<DevelopersListModel> {
        return runCatching {
            developersService.getDevelopersListData(
                sortType = null,
                part = part,
                page = page,
            ).data.toDevelopersListModel()
        }
    }

    override suspend fun getDevelopersDetailData(
        developerId: Int,
    ): Result<DevelopersDetailModel> {
        return runCatching {
            developersService.getDevelopersDetailData(developerId).data.toDevelopersDetailModel()
        }
    }

    override suspend fun createDeveloperInfo(
        accessToken: String,
        email: String?,
        submitDevInfo: SubmitDevInfo,
    ): Result<Int> {
        return runCatching {
            val bearerToken = "Bearer $accessToken"
            developersService.createDeveloperInfo(
                bearerToken,
                email,
                submitDevInfo.toCreateDevelopersRequest(),
            ).data
        }
    }
}
