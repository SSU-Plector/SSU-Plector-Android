package com.zucchini.ai_members.matching

import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.ai_members.designer.branding.BrandingFragment
import com.zucchini.common.NavigationProvider
import com.zucchini.feature.projects.databinding.ActivityAiMatchingBotBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AiMatchingBotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAiMatchingBotBinding
    private val viewModel by viewModels<AiMatchingBotViewModel>()

    override fun onResume() {
        super.onResume()
        initFragmentView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiMatchingBotBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFragmentView()

    }

    private fun initFragmentView() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.vpMatchingBot.id, SelectMatchingConditionsFragment())
            .commit()
    }

}