package com.zucchini.projects.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MypageViewModel @Inject constructor(
    private val preference: NetworkPreference,
    private val authRepository: AuthRepository,

) : ViewModel() {

    private val _logoutSuccess = MutableStateFlow<Boolean>(false)
    val logoutSuccess = _logoutSuccess.asStateFlow()

    private val _withdrawalSuccess = MutableStateFlow<Boolean>(false)
    val withdrawalSuccess = _withdrawalSuccess.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            authRepository.logout(preference.accessToken).onSuccess {
                preference.clear()
                _logoutSuccess.value = true
            }.onFailure {
                _logoutSuccess.value = false
                Timber.d("failed to logout $it")
            }
        }
    }

    fun withdrawal() {
        viewModelScope.launch {
            authRepository.withdrawal(preference.accessToken).onSuccess {
                preference.clear()
                kakaoUnlink()
            }.onFailure {
                _withdrawalSuccess.value = false
                Timber.d("failed to withdrawal")
            }
        }
    }

    private fun kakaoUnlink() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                _withdrawalSuccess.value = false
                Timber.d("failed to unlink: $error")
            } else {
                _withdrawalSuccess.value = true
            }
        }
    }
}
