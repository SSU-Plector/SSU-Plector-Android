package com.zucchini.projects.developer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.feature.projects.databinding.ActivityDevDetailBinding
import com.zucchini.projects.developer.adapter.DevDetailProjectAdapter
import com.zucchini.projects.developer.viewmodel.DevDetailViewModel
import com.zucchini.projects.dummy.DevProjectsDummy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DevDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevDetailBinding
    private val viewModel: DevDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDevDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        collectDevelopersDetail()
        initProjectAdapter()
    }

    private fun initProjectAdapter() {
        val projectAdapter = DevDetailProjectAdapter()
        binding.rvDevProject.layoutManager = LinearLayoutManager(this)
        binding.rvDevProject.adapter = projectAdapter
        projectAdapter.submitList(DevProjectsDummy.devProjectsInfoList)
    }

    private fun collectDevelopersDetail() {
        viewModel.developersDetail
            .flowWithLifecycle(lifecycle)
            .onEach {
                // developerInfoAdapter.submitList(it.developersList)
            }
            .launchIn(lifecycleScope)
    }
}
