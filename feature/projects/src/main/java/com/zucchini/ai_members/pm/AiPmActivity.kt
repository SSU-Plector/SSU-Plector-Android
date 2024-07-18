package com.zucchini.ai_members.pm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.domain.model.ai.SetProgressMeeting
import com.zucchini.feature.projects.databinding.ActivityAiPmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AiPmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAiPmBinding
    private val viewModel: AiPmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiPmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        updateProgressInfo()
        clickProgressMeetingResult()
    }

    private fun updateProgressInfo() {
        val progressMeetingInfo =
            SetProgressMeeting.ProgressMeetingInfo(
                introduceMyself = binding.cbAiPmMeetingProgressIntroduceMyself.isChecked,
                iceBreaking = binding.cbAiPmMeetingProgressIceBreaking.isChecked,
                brainstorming = binding.cbAiPmMeetingProgressBrainsTorming.isChecked,
                topicSelection = binding.cbAiPmMeetingProgressTopic.isChecked,
                progressSharing = binding.cbAiPmMeetingProgressProgress.isChecked,
                roleDivision = binding.cbAiPmMeetingProgressRole.isChecked,
                troubleShooting = binding.cbAiPmMeetingProgressTroubleshooting.isChecked,
                feedback = binding.cbAiPmMeetingProgressFeedback.isChecked,
            )
        val setProgressMeeting =
            SetProgressMeeting(
                meetingTime =
                    binding.etProgressTotalTime.text
                        .toString()
                        .toInt(),
                participants =
                    binding.etProgressParticipants.text
                        .toString()
                        .toInt(),
                progressMeetingInfo = progressMeetingInfo,
            )

        viewModel.updateProgressMeetingInfo(setProgressMeeting)
    }

    private fun clickProgressMeetingResult() {
        binding.btnSubmit.setOnClickListener {
            viewModel.sendProgressMeetingInfo()
        }
    }
}
