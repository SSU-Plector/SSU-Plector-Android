package com.zucchini.ai_members.matching

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.feature.projects.databinding.ActivityAiMatchingBotBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AiMatchingBotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAiMatchingBotBinding
    private val viewModel by viewModels<AiMatchingBotViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiMatchingBotBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}