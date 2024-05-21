package com.zucchini.projects.developer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.zucchini.core.designsystem.R
import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.feature.projects.databinding.ActivityDevDetailBinding
import com.zucchini.projects.developer.adapter.DevDetailProjectAdapter
import com.zucchini.projects.developer.viewmodel.DevDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DevDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevDetailBinding
    private val viewModel: DevDetailViewModel by viewModels()
    private val projectAdapter = DevDetailProjectAdapter()

    override fun onResume() {
        val developerId = intent.getIntExtra("developerId", 0)
        loadDevDetail(developerId)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDevDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        collectProjectList()
        collectDevDetail()
        initProjectAdapter()
        backClickListner()
    }

    private fun loadDevDetail(projectId: Int) {
        viewModel.loadDevelopersDetail(projectId)
    }

    private fun backClickListner() {
        binding.ivBackButton.setOnClickListener {
            finish()
        }
    }

    private fun initProjectAdapter() {
        binding.run {
            rvDevProject.layoutManager = LinearLayoutManager(this@DevDetailActivity)
            rvDevProject.adapter = projectAdapter
            rvDevProject.isNestedScrollingEnabled = false
        }
    }

    private fun collectProjectList() {
        viewModel.projectListInDevDetail
            .flowWithLifecycle(lifecycle)
            .onEach {
                projectAdapter.submitList(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun collectDevDetail() {
        viewModel.developersDetail
            .flowWithLifecycle(lifecycle)
            .onEach {
                bindDevDetailView(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun bindDevDetailView(it: DevelopersDetailModel?) {
        binding.run {
            ivProjectDetail.load(
                it?.imageLink?.toIntOrNull()
                    ?: R.drawable.project_profile_default,
            )
            tvProjectName.text = it?.name
            tvDevMajor.text = "${it?.university} ${it?.major} ${it?.studentNumber}"
            tvDevPosition1.text = it?.part1
            tvDevPosition2.text = it?.part2
            tvGithub.text = it?.githubLink
            tvKakaoid.text = it?.kakaoId
            tvEmail.text = it?.email
            tvProjectIntroContentLong.text = it?.shortIntro
            tvDevStackTech1.text = it?.techStackList?.getOrNull(0)
            tvDevStackTech2.text = it?.techStackList?.getOrNull(1)
            tvDevStackTech3.text = it?.techStackList?.getOrNull(2)
            tvDevStackLanguage1.text = it?.languageList?.getOrNull(0)
            tvDevStackLanguage2.text = it?.languageList?.getOrNull(1)
            tvDevStackLanguage3.text = it?.languageList?.getOrNull(2)
            tvDevStackCooperation1.text = it?.devToolList?.getOrNull(0)
            tvDevStackCooperation2.text = it?.devToolList?.getOrNull(1)
            tvDevStackCooperation3.text = it?.devToolList?.getOrNull(2)
        }
    }
}
