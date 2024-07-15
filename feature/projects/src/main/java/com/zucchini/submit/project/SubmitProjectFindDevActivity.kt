package com.zucchini.submit.project

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectFindDevBinding
import com.zucchini.submit.project.adapter.FindDevAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SubmitProjectFindDevActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitProjectFindDevBinding
    private val viewModel: SubmitProjectViewModel by viewModels()
    private lateinit var devAdapter: FindDevAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySubmitProjectFindDevBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDevInfoAdapter()
        initBackButton()
        clickSearchKeyword()
    }

    private fun initDevInfoAdapter() {
        devAdapter = FindDevAdapter(viewModel.submitDevInfo.value ?: SubmitProjectInfo())
        binding.rvFindDev.adapter = devAdapter
        binding.rvFindDev.layoutManager = GridLayoutManager(this, 2)
    }

    private fun clickSearchKeyword() {
        binding.ivSearch.setOnClickListener {
            viewModel.searchDeveloperList(binding.etSearchbar.text.toString())
        }

        viewModel.searchDeveloperResultList.flowWithLifecycle(lifecycle).onEach { developerList ->
            devAdapter.submitList(developerList)
        }.launchIn(lifecycleScope)
    }

    private fun initBackButton() {
        binding.ivBackButton.setOnClickListener {
            finish()
        }
    }
}
