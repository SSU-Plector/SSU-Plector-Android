package com.zucchini.ai_members.designer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.repository.AiRepository
import com.zucchini.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AiDesignerViewModel @Inject constructor(
    private val aiDesignerRepository: AiRepository,
) : ViewModel() {

    private val _projectImagePath = MutableStateFlow(UiState.Initial as UiState<String>)
    val projectImagePath = _projectImagePath.asStateFlow()

    private val _projectBrandingText = MutableStateFlow(UiState.Initial as UiState<String>)
    val projectBrandingText = _projectBrandingText.asStateFlow()

    fun getProjectBranding(projectInfoTextRequest: String?) {
        _projectBrandingText.value = UiState.Loading
        viewModelScope.launch {
            aiDesignerRepository.getProjectBranding(projectInfoTextRequest).let { result ->
                result.onSuccess {
                    _projectBrandingText.value = UiState.Success(it)
                }
                result.onFailure {
                    Timber.d("Failed to get project branding text: $it")
                }
            }
        }
    }

    fun getProjectImage(projectImageRequest: String?) {
        _projectImagePath.value = UiState.Loading
        viewModelScope.launch {
            aiDesignerRepository.getProjectImage(projectImageRequest).let { result ->
                result.onSuccess {
                    _projectImagePath.value = UiState.Success(it)
                }
                result.onFailure {
                    Timber.d("Failed to get project image path: $it")
                }
            }
        }
    }

}