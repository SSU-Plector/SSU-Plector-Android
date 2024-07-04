package com.zucchini.submit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectBinding

class SubmitProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
