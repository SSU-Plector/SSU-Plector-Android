package com.zucchini.ai_members.pm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.zucchini.feature.projects.databinding.ActivityAiPmBinding
import dagger.hilt.android.AndroidEntryPoint

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
}
