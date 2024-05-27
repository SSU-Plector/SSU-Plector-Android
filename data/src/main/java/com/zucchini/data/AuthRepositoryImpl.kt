package com.zucchini.data

import com.sample.network.service.AuthService
import com.zucchini.domain.model.auth.Login
import com.zucchini.domain.repository.AuthRepository
import com.zucchini.mapper.toLogin
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {

    override suspend fun login(accessToken: String): Result<Login> {
        return runCatching {
            authService.kakaoLogin(accessToken).data.toLogin()
        }
    }

    override suspend fun logout(accessToken: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun withdrawal(accessToken: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
