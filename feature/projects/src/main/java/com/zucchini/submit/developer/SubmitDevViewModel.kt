package com.zucchini.submit.developer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.sample.network.datastore.NetworkPreference
import com.zucchini.domain.model.SubmitDevInfo
import com.zucchini.domain.repository.DevelopersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SubmitDevViewModel @Inject constructor(
    private val developersRepository: DevelopersRepository,
    private val networkPreference: NetworkPreference,
) : ViewModel() {

    private val _devGithub = MutableStateFlow<String>("")

    private val _devUniversity = MutableStateFlow<String>("")

    private val _devMajor = MutableStateFlow<String>("")

    private val _devIntro = MutableStateFlow<String>("")

    private val _devStudentNumber = MutableStateFlow<String>("")

    private val _devPart1 = MutableStateFlow<String>("")

    private val _devPart2 = MutableStateFlow<String>("")

    private val _devToolList = MutableStateFlow<List<String>>(emptyList())

    private val _devLanguageList = MutableStateFlow<List<String>>(emptyList())

    private val _devTechStackList = MutableStateFlow<List<String>>(emptyList())

    private val _kakaoId = MutableStateFlow<String>("")

    private val _submitDevInfoSuccess = MutableStateFlow<Boolean>(false)
    val submitDevInfoSuccess = _submitDevInfoSuccess.asStateFlow()

    private val _kakaoNickname = MutableStateFlow<String>("")
    val kakaoNickname = _kakaoNickname.asStateFlow()

    private val _kakaoImage = MutableStateFlow<String>("")
    val kakaoImage = _kakaoImage.asStateFlow()

    init {
        getKakaoUserInfo()
    }

    fun submitDev(submitDevInfo: SubmitDevInfo) {
        _devGithub.value = submitDevInfo.devGithub
        _devUniversity.value = submitDevInfo.devUniversity
        _devMajor.value = submitDevInfo.devMajor
        _devIntro.value = submitDevInfo.devIntro
        _devStudentNumber.value = submitDevInfo.devStudentNumber
        _devPart1.value = submitDevInfo.devPart1
        _devPart2.value = submitDevInfo.devPart2
        _devTechStackList.value = submitDevInfo.devTechStackList
        _devLanguageList.value = submitDevInfo.devLanguageList
        _devToolList.value = submitDevInfo.devCooperationList
        _kakaoId.value = submitDevInfo.kakaoId
    }

    fun createDeveloperInfo() {
        viewModelScope.launch {
            developersRepository.createDeveloperInfo(
                accessToken = networkPreference.accessToken,
                email = networkPreference.kakaoEmail,
                submitDevInfo = SubmitDevInfo(
                    devGithub = _devGithub.value,
                    devUniversity = _devUniversity.value,
                    devMajor = _devMajor.value,
                    devIntro = _devIntro.value,
                    devStudentNumber = _devStudentNumber.value,
                    kakaoId = _kakaoId.value,
                    devPart1 = _devPart1.value,
                    devPart2 = _devPart2.value,
                    devTechStackList = _devTechStackList.value,
                    devLanguageList = _devLanguageList.value,
                    devCooperationList = _devToolList.value,
                ),
            ).onSuccess {
                _submitDevInfoSuccess.value = true
            }.onFailure {
                _submitDevInfoSuccess.value = false
                Timber.e(it)
            }
        }
    }

    private fun getKakaoUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Timber.e(this.toString())
            } else if (user != null) {
                _kakaoImage.value =
                    user.kakaoAccount?.profile?.thumbnailImageUrl ?: ""
                _kakaoNickname.value = user.kakaoAccount?.profile?.nickname ?: "쥬키니"
            }
        }
    }
}
