package com.zucchini.ai_members.matching

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.ai.DeveloperInfo
import com.zucchini.domain.model.ai.DeveloperMatchingInfo
import com.zucchini.domain.model.ai.MatchingResult
import com.zucchini.domain.repository.AiRepository
import com.zucchini.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AiMatchingBotViewModel @Inject constructor(
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _devLanguage = MutableStateFlow(emptyList<String>())

    private val _devStack = MutableStateFlow(emptyList<String>())

    private val _devPart = MutableStateFlow(emptyList<String>())

    private val _minStudentId = MutableStateFlow(11)

    private val _maxStudentId = MutableStateFlow(11)

    private val _projectExperience = MutableStateFlow(false)

    private val _additionalExperience = MutableStateFlow("")

    private val _resultSuccess = MutableStateFlow(UiState.Initial as UiState<List<MatchingResult>>)
    val resultSuccess = _resultSuccess.asStateFlow()

    private val _matchedDeveloperList = MutableStateFlow(emptyList<MatchingResult>())
    val matchedDeveloperList = _matchedDeveloperList.asStateFlow()

    fun updateMinStudentId(minStudentId: Int) {
        _minStudentId.value = minStudentId
    }

    fun updateMaxStudentId(maxStudentId: Int) {
        _maxStudentId.value = maxStudentId
    }

    fun updateProjectExperience(projectExperience: Boolean) {
        _projectExperience.value = projectExperience
    }

    fun updateAdditionalExperience(additionalExperience: String) {
        _additionalExperience.value = additionalExperience
    }

    fun updateDevLanguage(devLanguage: List<String>) {
        _devLanguage.value = devLanguage
    }

    fun updateDevStack(devStack: List<String>) {
        _devStack.value = devStack
    }

    fun updateDevPart(devPart: List<String>) {
        _devPart.value = devPart
    }

    fun getMatchingResult() {
        val matchingInfo = DeveloperMatchingInfo(
            part = _devPart.value.firstOrNull(),
            languageList = _devLanguage.value,
            techStackList = _devStack.value,
            projectExperience = _projectExperience.value,
            studentNumberMin = _minStudentId.value,
            studentNumberMax = _maxStudentId.value
        )
        val developerInfo =  _additionalExperience.value
        viewModelScope.launch {
            Log.d("AiMatchingBotViewModel", "${matchingInfo}, ${developerInfo}")
            _resultSuccess.value = UiState.Loading
            aiRepository.getDevelopersMatchingResult(matchingInfo, developerInfo).let { result ->
                result.onSuccess {
                    _resultSuccess.value = UiState.Success(it)
                    _matchedDeveloperList.value = it
                    Log.d("AiMatchingBotViewModel", "Success to get matching result: ${it}")
                }
                result.onFailure {
                    Timber.d("Failed to get matching result: ${it.message}")
                }
            }
        }
    }
}