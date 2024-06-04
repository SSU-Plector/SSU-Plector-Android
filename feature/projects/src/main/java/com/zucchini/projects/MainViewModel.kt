package com.zucchini.projects

import androidx.lifecycle.ViewModel
import com.kakao.sdk.user.UserApiClient
import com.sample.network.datastore.NetworkPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkPreference: NetworkPreference,
) : ViewModel() {

    init {
        getKakaoUserInfo()
    }

    private fun getKakaoUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Timber.e("failed to get user info: $error")
            } else if (user != null) {
                networkPreference.kakaoNickname =
                    user.kakaoAccount?.profile?.nickname ?: "no nickname"
                networkPreference.kakaoEmail = user.kakaoAccount?.email ?: "no email"
                networkPreference.kakaoProfileImage =
                    user.kakaoAccount?.profile?.thumbnailImageUrl ?: ""
            }
        }
    }
}
