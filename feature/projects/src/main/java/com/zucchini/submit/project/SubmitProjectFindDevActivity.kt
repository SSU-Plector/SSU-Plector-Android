package com.zucchini.submit.project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectFindDevBinding
import com.zucchini.submit.project.adapter.FindDevAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubmitProjectFindDevActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitProjectFindDevBinding
    private val viewModel: SubmitProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySubmitProjectFindDevBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDevInfoAdapter()

    }

    private fun initDevInfoAdapter() {
        val devAdapter = FindDevAdapter(viewModel.submitDevInfo.value ?: SubmitProjectInfo())
        binding.rvFindDev.adapter = devAdapter
        binding.rvFindDev.layoutManager = GridLayoutManager(this, 3)
    }
}
