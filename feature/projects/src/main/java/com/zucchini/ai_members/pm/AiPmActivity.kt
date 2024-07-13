package com.zucchini.ai_members.pm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.feature.projects.databinding.ActivityAiPmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AiPmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAiPmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiPmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
