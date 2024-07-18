package com.zucchini.ai_members.pm

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.zucchini.ai_members.pm.progress.ProgressMeetingFragment
import com.zucchini.ai_members.pm.progress.StartProgressMeetingFragment
import com.zucchini.ai_members.pm.summary.MeetingSummaryFragment
import com.zucchini.feature.projects.databinding.ActivityAiPmBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AiPmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAiPmBinding
    private val viewModel: AiPmViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        initFragmentView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiPmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickTabListner()
        resultSummarySuccess()
    }

    private fun clickTabListner() {
        binding.tlAiPm.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.vpAiPm.id, MeetingSummaryFragment())
                                .commit()
                        }

                        1 -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.vpAiPm.id, ProgressMeetingFragment())
                                .commit()
                        }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // Handle tab reselect
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // Handle tab unselect
                }
            },
        )
    }

    private fun initFragmentView() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.vpAiPm.id, MeetingSummaryFragment())
            .commit()
    }

    private fun resultSummarySuccess() {
        viewModel.summarySuccess.onEach { success ->
            if (success) {
                navigateToStartMeeting()
            } else {
                Log.e("MeetingSummaryFragment", "Meeting summary failed")
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToStartMeeting() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.vpAiPm.id, StartProgressMeetingFragment())
            .commit()
    }
}
