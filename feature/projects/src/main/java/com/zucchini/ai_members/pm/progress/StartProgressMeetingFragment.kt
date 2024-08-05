package com.zucchini.ai_members.pm.progress

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.zucchini.ai_members.pm.AiPmViewModel
import com.zucchini.domain.model.ai.ProgressMeeting
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentStartProgressMeetingBinding
import com.zucchini.uistate.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StartProgressMeetingFragment : Fragment() {
    private var _binding: FragmentStartProgressMeetingBinding? = null
    private val binding: FragmentStartProgressMeetingBinding get() = _binding!!

    private val viewModel: AiPmViewModel by activityViewModels()

    private val meetingList = mutableListOf<StartMeeting>()
    var ttsObj: TextToSpeech? = null


    data class StartMeeting(
        val progressName: String,
        val progressTime: Int,
        val speaking: String = "",
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartProgressMeetingBinding.inflate(inflater, container, false)

        collectProgressMeetingInfo()
        clickStartMeetingProgress()
        setTTS()

        return binding.root
    }

    private fun setTTS() {
        ttsObj = TextToSpeech(requireContext(), TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                ttsObj?.language = java.util.Locale.KOREAN
            }
        })
    }

    private fun startSpeaking(speaking: String) {
        ttsObj?.speak(speaking, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun collectProgressMeetingInfo() {
        viewModel.progressMeetingResultData.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when (it) {
                    is UiState.Initial -> {
                        // 초기 화면
                    }
                    is UiState.Loading -> {
                        // 로딩 화면
                    }
                    is UiState.Success -> {
                        setMeetingIndex(it.data)
                    }
                    is UiState.Failure -> {
                        // 에러 화면
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setMeetingIndex(it: ProgressMeeting?) {
        val totalTime = msToMinuteToString(viewModel.meetingTotalTime.value)
        binding.tvTotalTimeMinute.text = totalTime

        if (it?.introduceMyself!! < 0) {
            binding.tvIntroduceMinute.isVisible = false
            binding.tvIntroduceTime.isVisible = false
        } else {
            val introduceTime = msToMinuteToString(it.introduceMyself)

            binding.tvIntroduceMinute.isVisible = true
            binding.tvIntroduceTime.isVisible = true
            binding.tvIntroduceMinute.text = introduceTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.introduceTitle), it.introduceMyself!!,
                    getString(R.string.speakIntroduce, introduceTime)
                )
            )
        }

        if (it.iceBreaking!! < 0) {
            binding.tvIcebreakingMinute.isVisible = false
            binding.tvIcebreakingTime.isVisible = false
        } else {
            val iceBreakingTime = msToMinuteToString(it.iceBreaking)

            binding.tvIcebreakingMinute.isVisible = true
            binding.tvIcebreakingTime.isVisible = true
            binding.tvIcebreakingMinute.text = iceBreakingTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.iceBreakingTitle),
                    it.iceBreaking!!,
                    getString(R.string.speakIceBreaking, iceBreakingTime)
                )
            )
        }

        if (it.brainstorming!! < 0) {
            binding.tvBrainstormingMinute.isVisible = false
            binding.tvBrainstormingTime.isVisible = false
        } else {
            val brainstormingTime = msToMinuteToString(it.brainstorming)

            binding.tvBrainstormingMinute.isVisible = true
            binding.tvBrainstormingTime.isVisible = true
            binding.tvBrainstormingMinute.text = brainstormingTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.brainstormingTitle),
                    it.brainstorming!!,
                    getString(R.string.speakBrainstorming, brainstormingTime)
                )
            )
        }

        if (it.topicSelection!! < 0) {
            binding.tvTopicMinute.isVisible = false
            binding.tvTopicTime.isVisible = false
        } else {
            val topicTime = msToMinuteToString(it.topicSelection)

            binding.tvTopicMinute.isVisible = true
            binding.tvTopicTime.isVisible = true
            binding.tvTopicMinute.text = topicTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.topicTitle),
                    it.topicSelection!!,
                    getString(R.string.speakTopic, topicTime)
                )
            )
        }

        if (it.progressSharing!! < 0) {
            binding.tvCurrentProgressMinute.isVisible = false
            binding.tvCurrentProgressTime.isVisible = false
        } else {
            val progressSharingTime = msToMinuteToString(it.progressSharing)

            binding.tvCurrentProgressMinute.isVisible = true
            binding.tvCurrentProgressTime.isVisible = true
            binding.tvCurrentProgressMinute.text = progressSharingTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.currentProgressTitle),
                    it.progressSharing!!,
                    getString(R.string.speakCurrentProgress, progressSharingTime)
                )
            )
        }

        if (it.roleDivision!! < 0) {
            binding.tvRoleMinute.isVisible = false
            binding.tvRoleTime.isVisible = false
        } else {
            val roleDivisionTime = msToMinuteToString(it.roleDivision)

            binding.tvRoleMinute.isVisible = true
            binding.tvRoleTime.isVisible = true
            binding.tvRoleMinute.text = roleDivisionTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.roleDivisionTitle),
                    it.roleDivision!!,
                    getString(R.string.speakRoleDivision, roleDivisionTime)
                )
            )
        }

        if (it.troubleShooting!! < 0) {
            binding.tvTroubleShootingMinute.isVisible = false
            binding.tvTroubleShootingTime.isVisible = false
        } else {
            val troubleShootingTime = msToMinuteToString(it.troubleShooting)

            binding.tvTroubleShootingMinute.isVisible = true
            binding.tvTroubleShootingTime.isVisible = true
            binding.tvTroubleShootingMinute.text = troubleShootingTime

            meetingList.add(
                StartMeeting(
                    getString(R.string.troubleShootingTitle),
                    it.troubleShooting!!,
                    getString(R.string.speakTroubleShooting, troubleShootingTime)
                )
            )
        }

        if (it.feedback!! < 0) {
            binding.tvFeedbackMinute.isVisible = false
            binding.tvFeedbackTime.isVisible = false
        } else {
            val feedbackTime = msToMinuteToString(it.feedback)

            binding.tvFeedbackMinute.isVisible = true
            binding.tvFeedbackTime.isVisible = true
            binding.tvFeedbackMinute.text = feedbackTime
            meetingList.add(
                StartMeeting(
                    getString(R.string.feedbackTitle),
                    it.feedback!!,
                    getString(R.string.speakFeedback, feedbackTime)
                )
            )
        }
    }

    private fun msToMinuteToString(ms: Int?): String {
        if (ms == null) return "0분"
        val minutes = (ms / 60000)
        return String.format("%02d분", minutes)
    }

    private fun clickStartMeetingProgress() {
        binding.tvStartMeeting.setOnClickListener {
            if (meetingList.isNotEmpty()) {
                startMeetingProgress(0) // 첫 번째 항목부터 시작
            }
        }
    }

    private fun startMeetingProgress(currentIndex: Int) {
        if (currentIndex < meetingList.size) {
            val currentMeeting = meetingList[currentIndex]

            startSpeaking(meetingList[currentIndex].speaking)
            // 현재 회의 항목을 UI에 반영
            binding.tvCurrentMeetingProgress.text = currentMeeting.progressName
            binding.pbTimer.max = currentMeeting.progressTime // 밀리초 단위로 설정
            binding.pbTimer.progress = 0

            // 타이머 시작
            viewLifecycleOwner.lifecycleScope.launch {
                for (timeRemaining in currentMeeting.progressTime / 1000 downTo 0) {
                    if (!isActive) return@launch // 코루틴이 활성 상태인지 확인

                    val timeRemainingMs = timeRemaining * 1000
                    binding.tvTimer.text = intToTimerString(timeRemainingMs)
                    binding.pbTimer.progress = currentMeeting.progressTime - timeRemainingMs
                    delay(1000L) // 1초 대기
                }

                startMeetingProgress(currentIndex + 1) // 다음 항목으로 진행
            }
        } else {
            // 모든 항목이 끝난 후 완료 문구 표시
            binding.tvDone.isVisible = true
            binding.tvTimer.text = getString(R.string._00_00)
            binding.pbTimer.progress = binding.pbTimer.max
        }
    }

    private fun intToTimerString(totalTimeMs: Int): String {
        val totalSeconds = totalTimeMs / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (ttsObj != null) {
            ttsObj?.stop()
            ttsObj?.shutdown()
            ttsObj = null
        }
    }
}
