package com.zucchini.ai_members.pm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.domain.repository.AiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AiPmViewModel @Inject constructor(
    private val aiPmRepository: AiRepository,
): ViewModel() {
    private var _progressMeetingInfo = MutableStateFlow<SetProgressMeeting>(null!!)
    var progressMeetingInfo = _progressMeetingInfo.asStateFlow()

    private val _progressMeetingResultData = MutableStateFlow<ProgressMeeting>(null!!)

    fun updateProgressMeetingInfo(setProgressMeeting: SetProgressMeeting) {
        _progressMeetingInfo.value = setProgressMeeting
    }

    fun sendProgressMeetingInfo() {
        viewModelScope.launch {
            aiPmRepository.getProgressMeetingData(_progressMeetingInfo.value).onSuccess {
                _progressMeetingResultData.value = it
            }.onFailure {
                Timber.d(it)
            }
        }
    }
}
