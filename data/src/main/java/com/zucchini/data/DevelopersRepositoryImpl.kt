package com.zucchini.data

import com.sample.network.service.DevelopersService
import com.zucchini.domain.model.FindDeveloperInfo
import com.zucchini.domain.model.developers.DevelopersDetailModel
import com.zucchini.domain.model.developers.DevelopersListModel
import com.zucchini.domain.model.submit.SubmitDevInfo
import com.zucchini.domain.repository.DevelopersRepository
import com.zucchini.mapper.toCreateDevelopersRequest
import com.zucchini.mapper.toDevelopersDetailModel
import com.zucchini.mapper.toDevelopersListModel
import com.zucchini.mapper.toSearchDeveloperResult
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

    override suspend fun searchDevelopers(developerName: String): Result<List<FindDeveloperInfo>> {
        return runCatching {
            developersService.searchDevelopers(developerName).data.toSearchDeveloperResult()
        }
    }
}
