package com.zucchini.projects.projects

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.zucchini.core.designsystem.R
import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.feature.projects.databinding.ActivityProjectDetailBinding
import com.zucchini.projects.projects.adapter.ProjectDetailDevAdapter
import com.zucchini.projects.projects.viewmodel.ProjectDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProjectDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProjectDetailBinding

    private val viewModel by viewModels<ProjectDetailViewModel>()
    private val adapter = ProjectDetailDevAdapter()
    private var projectId: Int = 0

    override fun onResume() {
        projectId = intent.getIntExtra("projectId", 0)
        initProjectId(projectId)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDevInfoAdapter()
        collectProjectDetailData()
        backClickListner()
    }

    private fun backClickListner() {
        binding.ivBackButton.setOnClickListener {
            finish()
        }
    }

    private fun initProjectId(projectId: Int) {
        viewModel.loadProjectsDetail(projectId)
    }

    private fun initDevInfoAdapter() {
        binding.rvProjectDetailDev.adapter = adapter
        binding.rvProjectDetailDev.layoutManager = GridLayoutManager(this, 3)
    }

    private fun collectProjectDetailData() {
        viewModel.devListInProjectsDetail
            .flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it)
            }
            .launchIn(lifecycleScope)

        viewModel.projectsDetail.flowWithLifecycle(lifecycle)
            .onEach {
                bindProjectDetailView(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun bindProjectDetailView(it: ProjectsDetailModel?) {
        binding.run {
            ivProjectDetail.load(
                it?.imageLink
                    ?: R.drawable.project_profile_default,
            )
            val projectGithubLink = it?.githubLink ?: getString(com.zucchini.feature.projects.R.string.github_default_link)

            tvProjectName.text = it?.name
            tvProjectSorted.text = it?.category
            tvProjectClicked.text = "조회수 +${it?.hits}"
            tvProjectIntroContent.text = it?.shortIntro
            tvProjectIntroContentLong.text = it?.longIntro
            tvGithub.text = projectGithubLink
            tvAppLink.text = it?.appLink
            tvLandingLink.text = it?.infoPageLink
            tvWebLink.text = it?.webLink
            tvDevStackLanguage1.text = it?.languageList?.get(0) ?: ""
            tvDevStackLanguage2.text = it?.languageList?.get(1) ?: ""
            tvDevStackLanguage3.text = it?.languageList?.get(2) ?: ""
            tvDevStackCooperation1.text = it?.devToolList?.get(0) ?: ""
            tvDevStackCooperation2.text = it?.devToolList?.get(1) ?: ""
            tvDevStackCooperation3.text = it?.devToolList?.get(2) ?: ""
            tvDevStackTech1.text = it?.techStackList?.get(0) ?: ""
            tvDevStackTech2.text = it?.techStackList?.get(1) ?: ""
            tvDevStackTech3.text = it?.techStackList?.get(2) ?: ""

            navigateToProjectGithubLink(projectGithubLink)
        }
    }

    private fun ActivityProjectDetailBinding.navigateToProjectGithubLink(
        projectGithubLink: String?,
    ) {
        tvGithub.setOnClickListener {
            // TODO : Github 링크 형식이 올바르지 않는 경우는 어떻게 할지?
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(projectGithubLink))
            startActivity(intent)
        }
    }
}
