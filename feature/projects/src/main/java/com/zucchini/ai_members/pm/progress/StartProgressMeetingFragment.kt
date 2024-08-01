package com.zucchini.ai_members.pm.progress

import android.os.Bundle
import android.os.CountDownTimer
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
                setMeetingIndex(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setMeetingIndex(it: ProgressMeeting?) {
        binding.tvTotalTimeMinute.text =
            getString(R.string.minute, msToMinuteToString(viewModel.meetingTotalTime.value))

        if (it?.introduceMyself!! < 0) {
            binding.tvIntroduceMinute.isVisible = false
            binding.tvIntroduceTime.isVisible = false
        } else {
            binding.tvIntroduceMinute.isVisible = true
            binding.tvIntroduceTime.isVisible = true
            binding.tvIntroduceMinute.text =
                getString(R.string.minute, msToMinuteToString(it.introduceMyself))

            meetingList.add(StartMeeting("introduceMyself", it.introduceMyself!!, "자기소개를 진행해주세요."))
        }

        if (it.iceBreaking!! < 0) {
            binding.tvIcebreakingMinute.isVisible = false
            binding.tvIcebreakingTime.isVisible = false
        } else {
            binding.tvIcebreakingMinute.isVisible = true
            binding.tvIcebreakingTime.isVisible = true
            binding.tvIcebreakingMinute.text =
                getString(R.string.minute, msToMinuteToString(it.iceBreaking))
            meetingList.add(StartMeeting("iceBreaking", it.iceBreaking!!))
        }

        if (it.brainstorming!! < 0) {
            binding.tvBrainstormingMinute.isVisible = false
            binding.tvBrainstormingTime.isVisible = false
        } else {
            binding.tvBrainstormingMinute.isVisible = true
            binding.tvBrainstormingTime.isVisible = true
            binding.tvBrainstormingMinute.text =
                getString(R.string.minute, msToMinuteToString(it.brainstorming))
            meetingList.add(StartMeeting("brainstorming", it.brainstorming!!))
        }

        if (it.topicSelection!! < 0) {
            binding.tvTopicMinute.isVisible = false
            binding.tvTopicTime.isVisible = false
        } else {
            binding.tvTopicMinute.isVisible = true
            binding.tvTopicTime.isVisible = true
            binding.tvTopicMinute.text =
                getString(R.string.minute, msToMinuteToString(it.topicSelection))
            meetingList.add(StartMeeting("topicSelection", it.topicSelection!!))
        }

        if (it.progressSharing!! < 0) {
            binding.tvCurrentProgressMinute.isVisible = false
            binding.tvCurrentProgressTime.isVisible = false
        } else {
            binding.tvCurrentProgressMinute.isVisible = true
            binding.tvCurrentProgressTime.isVisible = true
            binding.tvCurrentProgressMinute.text =
                getString(R.string.minute, msToMinuteToString(it.progressSharing))
            meetingList.add(StartMeeting("progressSharing", it.progressSharing!!))
        }

        if (it.roleDivision!! < 0) {
            binding.tvRoleMinute.isVisible = false
            binding.tvRoleTime.isVisible = false
        } else {
            binding.tvRoleMinute.isVisible = true
            binding.tvRoleTime.isVisible = true
            binding.tvRoleMinute.text =
                getString(R.string.minute, msToMinuteToString(it.roleDivision))
            meetingList.add(StartMeeting("roleDivision", it.roleDivision!!))
        }

        if (it.troubleShooting!! < 0) {
            binding.tvTroubleShootingMinute.isVisible = false
            binding.tvTroubleShootingTime.isVisible = false
        } else {
            binding.tvTroubleShootingMinute.isVisible = true
            binding.tvTroubleShootingTime.isVisible = true
            binding.tvTroubleShootingMinute.text =
                getString(R.string.minute, msToMinuteToString(it.troubleShooting))
            meetingList.add(StartMeeting("troubleShooting", it.troubleShooting!!))
        }

        if (it.feedback!! < 0) {
            binding.tvFeedbackMinute.isVisible = false
            binding.tvFeedbackTime.isVisible = false
        } else {
            binding.tvFeedbackMinute.isVisible = true
            binding.tvFeedbackTime.isVisible = true
            binding.tvFeedbackMinute.text =
                getString(R.string.minute, msToMinuteToString(it.feedback))
            meetingList.add(StartMeeting("feedback", it.feedback!!))
        }
    }

    private fun msToMinuteToString(ms: Int?): String = (ms?.div(60000)).toString()

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
            binding.pbTimer.max = currentMeeting.progressTime * 1000 // 밀리초 단위로 설정
            binding.pbTimer.progress = 0

            // 타이머 시작
            viewLifecycleOwner.lifecycleScope.launch {
                for (timeRemaining in currentMeeting.progressTime downTo 0) {
                    if (!isActive) return@launch // 코루틴이 활성 상태인지 확인

                    binding.tvTimer.text = intToTimerString(timeRemaining * 1000) // 밀리초로 변환하여 전달
                    binding.pbTimer.progress = (currentMeeting.progressTime - timeRemaining) * 1000
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
        if(ttsObj!=null){
            ttsObj?.stop()
            ttsObj?.shutdown()
            ttsObj = null
        }
    }
}
