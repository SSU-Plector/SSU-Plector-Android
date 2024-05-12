package com.zucchini.projects.projects

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.feature.projects.databinding.ActivityProjectDetailBinding
import com.zucchini.projects.dummy.ProjectDetailDevDummy
import com.zucchini.projects.projects.adapter.ProjectDetailDevAdapter
import com.zucchini.projects.projects.viewmodel.ProjectDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProjectDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProjectDetailBinding

    private val viewModel by viewModels<ProjectDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDevInfoAdapter()
        collectProjectDetailData()
    }

    private fun initDevInfoAdapter() {
        val adapter = ProjectDetailDevAdapter()
        binding.rvProjectDetailDev.adapter = adapter
        binding.rvProjectDetailDev.layoutManager = GridLayoutManager(this, 3)
        adapter.submitList(ProjectDetailDevDummy.projectDetailDevList)
    }

    private fun collectProjectDetailData() {
        viewModel.projectsDetail
            .flowWithLifecycle(lifecycle)
            .onEach {
                // developerInfoAdapter.submitList(it.developersList)
            }
            .launchIn(lifecycleScope)
    }
}
