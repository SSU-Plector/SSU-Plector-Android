package com.zucchini.data

import com.sample.network.service.DevelopersService
import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.domain.model.DevelopersListModel
import com.zucchini.domain.repository.DevelopersRepository
import com.zucchini.mapper.toDevelopersDetailModel
import com.zucchini.mapper.toDevelopersListModel
import javax.inject.Inject

class DevelopersRepositoryImpl @Inject constructor(
    private val developersService: DevelopersService,
) : DevelopersRepository {
    override suspend fun getDevelopersListData(): Result<DevelopersListModel> {
        return runCatching {
            developersService.getDevelopersListData(
                sortType = null,
                part = null,
                page = 0,
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
}
