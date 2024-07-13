package com.zucchini.ai_members.designer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.feature.projects.databinding.ActivityAiDesignerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AiDesignerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAiDesignerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiDesignerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
