package com.zucchini.submit.project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectBinding
import com.zucchini.submit.project.adapter.SubmitProjectPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubmitProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitProjectBinding
    private val viewModel: SubmitProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
    }

    private fun initViews() {
        binding.vpSubmitProject.adapter = SubmitProjectPagerAdapter(this)
        binding.vpSubmitProject.isUserInputEnabled = false
    }

    private fun initListeners() {
        binding.ivBackButton.setOnClickListener {
            handleBackNavigation()
        }
    }

    private fun handleBackNavigation() {
        if (binding.vpSubmitProject.currentItem > 0) {
            binding.vpSubmitProject.currentItem -= 1
        } else {
            finish()
        }
    }

    fun setCurrentItem(item: Int) {
        binding.vpSubmitProject.setCurrentItem(item, true)
    }
}
