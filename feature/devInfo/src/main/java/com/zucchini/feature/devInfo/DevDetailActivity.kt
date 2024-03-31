package com.zucchini.feature.devInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.feature.devInfo.databinding.ActivityDevDetailBinding

class DevDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDevDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
