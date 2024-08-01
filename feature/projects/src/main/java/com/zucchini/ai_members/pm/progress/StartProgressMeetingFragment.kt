package com.zucchini.ai_members.pm.progress

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    private val meetingList = emptyList<StartMeeting>()

    data class StartMeeting(
        val progressName:String,
        val progressTime:Int,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartProgressMeetingBinding.inflate(inflater, container, false)

        collectProgressMeetingInfo()
        clickStartMeetingProgress()

        return binding.root
    }

    private fun collectProgressMeetingInfo() {
        viewModel.progressMeetingResultData.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {

                Log.d("ProgressMeetingFragment", "collectProgressMeetingInfo: $it")

                binding.tvTotalTimeMinute.text =
                    getString(R.string.minute, msToMinuteToString(viewModel.meetingTotalTime.value))

                if (it?.introduceMyself!! < 0) {
                    binding.tvIntroduceMinute.isVisible = false
                    binding.tvIntroduceTime.isVisible = false
                } else {
                    binding.tvIntroduceMinute.isVisible = true
                    binding.tvIntroduceTime.isVisible = true
                    binding.tvIntroduceMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.introduceMyself))
                }

                if (it?.iceBreaking!! < 0) {
                    binding.tvIcebreakingMinute.isVisible = false
                    binding.tvIcebreakingTime.isVisible = false
                } else {
                    binding.tvIcebreakingMinute.isVisible = true
                    binding.tvIcebreakingTime.isVisible = true
                    binding.tvIcebreakingMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.iceBreaking))
                }

                if (it?.brainstorming!! < 0) {
                    binding.tvBrainstormingMinute.isVisible = false
                    binding.tvBrainstormingTime.isVisible = false
                } else {
                    binding.tvBrainstormingMinute.isVisible = true
                    binding.tvBrainstormingTime.isVisible = true
                    binding.tvBrainstormingMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.brainstorming))
                }

                if (it?.topicSelection!! < 0) {
                    binding.tvTopicMinute.isVisible = false
                    binding.tvTopicTime.isVisible = false
                } else {
                    binding.tvTopicMinute.isVisible = true
                    binding.tvTopicTime.isVisible = true
                    binding.tvTopicMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.topicSelection))
                }

                if (it?.progressSharing!! < 0) {
                    binding.tvCurrentProgressMinute.isVisible = false
                    binding.tvCurrentProgressTime.isVisible = false
                } else {
                    binding.tvCurrentProgressMinute.isVisible = true
                    binding.tvCurrentProgressTime.isVisible = true
                    binding.tvCurrentProgressMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.progressSharing))
                }

                if (it?.roleDivision!! < 0) {
                    binding.tvRoleMinute.isVisible = false
                    binding.tvRoleTime.isVisible = false
                } else {
                    binding.tvRoleMinute.isVisible = true
                    binding.tvRoleTime.isVisible = true
                    binding.tvRoleMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.roleDivision))
                }

                if (it?.troubleShooting!! < 0) {
                    binding.tvTroubleShootingMinute.isVisible = false
                    binding.tvTroubleShootingTime.isVisible = false
                } else {
                    binding.tvTroubleShootingMinute.isVisible = true
                    binding.tvTroubleShootingTime.isVisible = true
                    binding.tvTroubleShootingMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.troubleShooting))
                }

                if (it?.feedback !!< 0) {
                    binding.tvFeedbackMinute.isVisible = false
                    binding.tvFeedbackTime.isVisible = false
                } else {
                    binding.tvFeedbackMinute.isVisible = true
                    binding.tvFeedbackTime.isVisible = true
                    binding.tvFeedbackMinute.text =
                        getString(R.string.minute, msToMinuteToString(it?.feedback))
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun msToMinuteToString(ms: Int?): String = (ms?.div(60000)).toString()

    private fun clickStartMeetingProgress() {
        binding.tvStartMeeting.setOnClickListener {

            // 타이머 시작
            // 프로그레스바 set
        }
    }
}
