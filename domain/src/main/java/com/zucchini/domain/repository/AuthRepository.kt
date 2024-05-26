package com.zucchini.domain.repository

import com.zucchini.domain.model.auth.Login

interface AuthRepository {

    suspend fun login(accessToken: String): Result<Login>
    suspend fun logout(accessToken: String): Result<Unit>
    suspend fun withdrawal(accessToken: String): Result<Unit>

}