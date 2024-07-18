package com.zucchini.ai_members.pm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.ProgressMeetingInfo
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.domain.repository.AiRepository
import com.zucchini.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AiPmViewModel
    @Inject
    constructor(
        private val aiPmRepository: AiRepository,
    ) : ViewModel() {
        private var _progressMeetingInfo = MutableStateFlow<SetProgressMeeting?>(null)

        private val _progressMeetingCheckbox =
            MutableStateFlow<ProgressMeetingInfo>(ProgressMeetingInfo())

        private val _progressMeetingResultData = MutableStateFlow<ProgressMeeting?>(null)

        private val _meetingSummaryResultText = MutableStateFlow(UiState.Initial as UiState<String>)
        val meetingSummaryResultText = _meetingSummaryResultText.asStateFlow()

        fun updateProgressMeetingCheckbox(
            setProgressMeeting: SetProgressMeeting,
            progressMeetingInfo: ProgressMeetingInfo,
        ) {
            _progressMeetingInfo.value = setProgressMeeting
            _progressMeetingCheckbox.value = progressMeetingInfo
        }

        fun sendProgressMeetingInfo() {
            viewModelScope.launch {
                aiPmRepository
                    .getProgressMeetingData(
                        _progressMeetingInfo.value,
                        _progressMeetingCheckbox.value,
                    ).onSuccess {
                        _progressMeetingResultData.value = it
                    }.onFailure {
                        Timber.d(it)
                    }
            }
        }

        fun sendMeetingRecordFile(recordFilePath: String) {
            viewModelScope.launch {
                _meetingSummaryResultText.value = UiState.Loading
                try {
                    val result = aiPmRepository.sendMeetingRecordFile(recordFilePath)
                    result
                        .onSuccess {
                            _meetingSummaryResultText.value = UiState.Success(it)
                        }.onFailure {
                            _meetingSummaryResultText.value = UiState.Failure(it.message ?: "Unknown error")
                            Timber.d(it)
                        }
                } catch (e: Exception) {
                    _meetingSummaryResultText.value = UiState.Failure(e.message ?: "Unknown error")
                    Timber.e(e)
                }
            }
        }
    }
