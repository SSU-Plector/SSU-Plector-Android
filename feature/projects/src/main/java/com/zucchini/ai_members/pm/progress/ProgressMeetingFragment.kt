package com.zucchini.ai_members.pm.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zucchini.ai_members.pm.AiPmViewModel
import com.zucchini.domain.model.ai.ProgressMeetingInfo
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.feature.projects.databinding.FragmentProgressMeetingBinding
import com.zucchini.view.hideKeyboard

class ProgressMeetingFragment : Fragment() {
    private var _binding: FragmentProgressMeetingBinding? = null
    private val binding: FragmentProgressMeetingBinding get() = _binding!!

    private val viewModel: AiPmViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProgressMeetingBinding.inflate(inflater, container, false)
        clickHideKeyboard()
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        clickListnerSubmitButton()
    }

    private fun clickListnerSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            setMeetingTimeAndParticipants()
            viewModel.sendProgressMeetingInfo()
        }
    }

    private fun setMeetingTimeAndParticipants() {
        val meetingTime = binding.etProgressTotalTime.text.toString()
        val participants = binding.etProgressParticipants.text.toString()

        // 기본값 설정 또는 유효성 검사
        val meetingTimeInt = if (meetingTime.isEmpty()) 0 else meetingTime.toInt()
        val participantsInt = if (participants.isEmpty()) 0 else participants.toInt()


        val progressMeetingCheckbox =
            ProgressMeetingInfo(
                introduceMyself = binding.cbAiPmMeetingProgressIntroduceMyself.isChecked,
                iceBreaking = binding.cbAiPmMeetingProgressIceBreaking.isChecked,
                brainstorming = binding.cbAiPmMeetingProgressBrainsTorming.isChecked,
                topicSelection = binding.cbAiPmMeetingProgressTopic.isChecked,
                progressSharing = binding.cbAiPmMeetingProgressProgress.isChecked,
                roleDivision = binding.cbAiPmMeetingProgressRole.isChecked,
                troubleShooting = binding.cbAiPmMeetingProgressTroubleshooting.isChecked,
                feedback = binding.cbAiPmMeetingProgressFeedback.isChecked,
            )

        val setProgressMeetingInfo =
            SetProgressMeeting(
                meetingTime = minuteToMs(meetingTimeInt),
                participants = participantsInt,
            )
        viewModel.updateProgressMeetingCheckbox(setProgressMeetingInfo, progressMeetingCheckbox)
    }

    private fun clickHideKeyboard() {
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun minuteToMs(minute: Int) = minute * 60000
}
