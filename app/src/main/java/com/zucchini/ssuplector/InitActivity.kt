package com.zucchini.ssuplector

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.projects.MainActivity
import com.zucchini.ssuplector.databinding.ActivityInitBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class InitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInitBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
