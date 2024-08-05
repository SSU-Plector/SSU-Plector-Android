package com.zucchini.ai_members.pm

import android.util.Log
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

    private val _progressMeetingCheckbox = MutableStateFlow(ProgressMeetingInfo())

    private val _progressMeetingResultData = MutableStateFlow(UiState.Initial as UiState<ProgressMeeting>)
    val progressMeetingResultData = _progressMeetingResultData.asStateFlow()

    private val _meetingTotalTime = MutableStateFlow(0)
    val meetingTotalTime = _meetingTotalTime.asStateFlow()

    private val _progressIntroduce = MutableStateFlow(0)
    val progressIntroduce = _progressIntroduce.asStateFlow()

    private val _progressIceBreaking = MutableStateFlow(0)
    val progressIceBreaking = _progressIceBreaking.asStateFlow()

    private val _progressBrainstorming = MutableStateFlow(0)
    val progressBrainstorming = _progressBrainstorming.asStateFlow()

    private val _progressTopicSelection = MutableStateFlow(0)
    val progressTopicSelection = _progressTopicSelection.asStateFlow()

    private val _progressSharing = MutableStateFlow(0)
    val progressSharing = _progressSharing.asStateFlow()

    private val _progressRoleDivision = MutableStateFlow(0)
    val progressRoleDivision = _progressRoleDivision.asStateFlow()

    private val _progressTroubleShooting = MutableStateFlow(0)
    val progressTroubleShooting = _progressTroubleShooting.asStateFlow()

    private val _progressFeedback = MutableStateFlow(0)
    val progressFeedback = _progressFeedback.asStateFlow()

    private val _meetingSummaryResultText = MutableStateFlow(UiState.Initial as UiState<String>)
    val meetingSummaryResultText = _meetingSummaryResultText.asStateFlow()

    private val _summarySuccess = MutableStateFlow(false)
    val summarySuccess = _summarySuccess.asStateFlow()

    fun updateProgressMeetingCheckbox(
        setProgressMeeting: SetProgressMeeting,
        progressMeetingInfo: ProgressMeetingInfo,
    ) {
        _meetingTotalTime.value = setProgressMeeting.meetingTime ?: 0
        Log.d("AiPmViewModel", "meetingTotalTime: ${setProgressMeeting.meetingTime}")
        _progressMeetingInfo.value = setProgressMeeting
        _progressMeetingCheckbox.value = progressMeetingInfo
    }

    fun sendProgressMeetingInfo() {
        viewModelScope.launch {
            _progressMeetingResultData.value = UiState.Loading

            aiPmRepository
                .getProgressMeetingData(
                    _progressMeetingInfo.value,
                    _progressMeetingCheckbox.value,
                ).onSuccess {
                    _progressMeetingResultData.value = UiState.Success(it)
                    _summarySuccess.value = true

                    Log.d("AiPmViewModel", "progressMeetingResultData: $it, progressIntroduce: ${it.introduceMyself}")
                    _progressIntroduce.value = it.introduceMyself ?: 0
                    _progressIceBreaking.value = it.iceBreaking ?: 0
                    _progressBrainstorming.value = it.brainstorming ?: 0
                    _progressTopicSelection.value = it.topicSelection ?: 0
                    _progressSharing.value = it.progressSharing ?: 0
                    _progressRoleDivision.value = it.roleDivision ?: 0
                    _progressTroubleShooting.value = it.troubleShooting ?: 0
                    _progressFeedback.value = it.feedback ?: 0

                }.onFailure {
                    Timber.d(it)
                    _summarySuccess.value = false
                    UiState.Failure(it.message ?: "Error")
                }
        }
    }

    fun sendMeetingRecordFile(recordFilePath: String) {
        viewModelScope.launch {
            _meetingSummaryResultText.value = UiState.Loading
            aiPmRepository.sendMeetingRecordFile(recordFilePath)
                .onSuccess {
                _meetingSummaryResultText.value = UiState.Success(it)
            }.onFailure {
                UiState.Failure(it.message ?: "Error")
            }

        }
    }
}
