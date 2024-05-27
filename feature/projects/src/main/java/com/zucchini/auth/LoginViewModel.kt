package com.zucchini.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.sample.network.datastore.NetworkPreference
import com.zucchini.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val networkPreference: NetworkPreference,
) : ViewModel() {

    private val _kakaoLoginSuccess = MutableStateFlow<Boolean>(false)
    val kakaoLoginSuccess = _kakaoLoginSuccess.asStateFlow()

    private val _isLogin = MutableStateFlow(false)
    val isLogin = _isLogin.asStateFlow()

    fun loginWithKakaoApp(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    loginWithKakaoAccount(context)
                } else if (token != null) {
                    viewModelScope.launch {
                        authRepository.login(token.accessToken).onSuccess {
                            networkPreference.run {
                                accessToken = it.accessToken
                                refreshToken = it.refreshToken
                                developerId = it.developerId
                                autoLoginConfigured = true
                            }
                            _isLogin.value = it.isLogin
                            _kakaoLoginSuccess.value = true
                            Timber.d("Login Success ${it.accessToken}")
                        }.onFailure {
                            _kakaoLoginSuccess.value = false
                        }
                    }
                }
            }
        } else {
            loginWithKakaoAccount(context)
        }
    }

    private fun loginWithKakaoAccount(context: Context) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                // 닉네임 정보 얻기 실패 시
            } else if (token != null) {
                viewModelScope.launch {
                    authRepository.login(token.accessToken).onSuccess {
                        networkPreference.run {
                            accessToken = it.accessToken
                            refreshToken = it.refreshToken
                            developerId = it.developerId
                            autoLoginConfigured = true
                        }
                        _isLogin.value = it.isLogin
                        _kakaoLoginSuccess.value = true
                        Timber.d("Login Success")
                        Timber.d("Login Success ${it.accessToken}")
                    }.onFailure {
                        _kakaoLoginSuccess.value = false
                        Timber.d("Login Fail")
                        Timber.d("Login token ${token.accessToken}")
                    }
                }
            }
        }
    }
}
