package com.zucchini.mapper

import com.sample.network.reponse.LoginResponse
import com.zucchini.domain.model.auth.Login

internal fun LoginResponse.toLogin(): Login {
    return Login(
        developerId = developerId ?: -1,
        accessToken = accessToken.orEmpty(),
        refreshToken = refreshToken.orEmpty(),
        isLogin = isLogin ?: false
    )
}
