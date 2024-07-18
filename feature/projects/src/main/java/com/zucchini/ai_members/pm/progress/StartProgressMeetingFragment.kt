package com.zucchini.ai_members.pm.progress

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.zucchini.ai_members.pm.AiPmViewModel
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentStartProgressMeetingBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class StartProgressMeetingFragment : Fragment() {
    private var _binding: FragmentStartProgressMeetingBinding? = null
    private val binding: FragmentStartProgressMeetingBinding get() = _binding!!

    private val viewModel: AiPmViewModel by activityViewModels()

    private var meetingTotalTime = 0
    private var introduce = 0
    private var iceBreaking = 0
    private var brainstorming = 0
    private var topicSelection = 0
    private var sharing = 0
    private var roleDivision = 0
    private var troubleShooting = 0
    private var feedback = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartProgressMeetingBinding.inflate(inflater, container, false)

        collectProgressMinutes()
        setMeetingIndex()

        return binding.root
    }

    private fun setMeetingIndex() {
        val formattedText = getString(
            R.string.tv_meeting_progress,
            msToMinuteToString(meetingTotalTime),
            msToMinuteToString(introduce),
            msToMinuteToString(iceBreaking),
            msToMinuteToString(brainstorming),
            msToMinuteToString(topicSelection),
            msToMinuteToString(sharing),
            msToMinuteToString(roleDivision),
            msToMinuteToString(troubleShooting),
            msToMinuteToString(feedback),
        )
        binding.tvMeetingProgress.text = formattedText
    }

    private fun collectProgressMinutes() {
        viewModel.progressIntroduce.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            introduce = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressIceBreaking.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            iceBreaking = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressBrainstorming.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            brainstorming = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressTopicSelection.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            topicSelection = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressSharing.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            sharing = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressRoleDivision.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            roleDivision = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressTroubleShooting.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            troubleShooting = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.progressFeedback.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            feedback = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.meetingTotalTime.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            Log.d("StartProgressMeetingFragment", "meetingTotalTime: $it")
            meetingTotalTime = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun msToMinuteToString(ms: Int): String = (ms / 60000).toString()
}
