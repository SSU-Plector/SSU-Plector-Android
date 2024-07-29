package com.zucchini.ai_members.matching

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AiMatchingBotViewModel @Inject constructor() : ViewModel() {

    private val _devLanguage = MutableStateFlow(emptyList<String>())

    private val _devStack = MutableStateFlow(emptyList<String>())

    private val _devPart = MutableStateFlow(emptyList<String>())

    private val _minStudentId = MutableStateFlow(0)

    private val _maxStudentId = MutableStateFlow(0)

    private val _projectExperience = MutableStateFlow(false)

    private val _additionalExperience = MutableStateFlow("")

    private val _resultSuccess = MutableStateFlow(false)
    val resultSuccess = _resultSuccess.asStateFlow()

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
}