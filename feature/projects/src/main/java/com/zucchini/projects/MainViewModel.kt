package com.zucchini.projects

import androidx.lifecycle.ViewModel
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _userNickname = MutableStateFlow("")
    val userNickname = _userNickname.asStateFlow()

    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    private val _userProfile = MutableStateFlow("")
    val userProfile = _userProfile.asStateFlow()

    init {
        getKakaoUserInfo()
    }

    private fun getKakaoUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                _userNickname.value = ""
                _userEmail.value = ""
                _userProfile.value = ""
            } else if (user != null) {
                _userNickname.value = user.kakaoAccount?.profile?.nickname ?: "no nickname"
                _userEmail.value = user.kakaoAccount?.email ?: "no email"
                _userProfile.value = user.kakaoAccount?.profile?.thumbnailImageUrl ?: ""
            }
        }
    }
}
