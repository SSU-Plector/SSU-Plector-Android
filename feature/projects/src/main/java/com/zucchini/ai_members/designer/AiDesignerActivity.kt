package com.zucchini.ai_members.designer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.zucchini.ai_members.designer.branding.BrandingFragment
import com.zucchini.ai_members.designer.image.ImageDesignFragment
import com.zucchini.ai_members.pm.progress.ProgressMeetingFragment
import com.zucchini.ai_members.pm.summary.MeetingSummaryFragment
import com.zucchini.feature.projects.databinding.ActivityAiDesignerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AiDesignerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAiDesignerBinding
    private val viewModel: AiDesignerViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        initFragmentView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiDesignerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickTabListner()
    }

    private fun clickTabListner() {
        binding.tlAiDesigner.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.vpAiDesigner.id, BrandingFragment())
                                .commit()
                        }

                        1 -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.vpAiDesigner.id, ImageDesignFragment())
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
            .replace(binding.vpAiDesigner.id, BrandingFragment())
            .commit()
    }
}
