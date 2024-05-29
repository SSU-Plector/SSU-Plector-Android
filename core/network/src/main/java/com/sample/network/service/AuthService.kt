package com.sample.network.service

import com.sample.network.model.BaseResponse
import com.sample.network.reponse.LoginResponse
import com.sample.network.reponse.RefreshResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {

    @GET("/api/auth/kakao/login")
    suspend fun kakaoLogin(
        @Query("code") accessToken: String? = "",
    ): BaseResponse<LoginResponse>

    @GET("/api/auth/kakao/refresh")
    suspend fun refreshToken(
        @Body refreshToken: String = "",
    ): BaseResponse<RefreshResponse>
}
