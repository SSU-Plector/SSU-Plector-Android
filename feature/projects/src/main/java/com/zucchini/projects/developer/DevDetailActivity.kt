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

            if (it?.devToolList?.size == 0 || it?.techStackList == null) {
                tvDevStackCooperation1.visibility = android.view.View.INVISIBLE
                tvDevStackCooperation2.visibility = android.view.View.INVISIBLE
                tvDevStackCooperation3.visibility = android.view.View.INVISIBLE
            } else if (it.devToolList?.size == 1) {
                tvDevStackCooperation1.text = it.devToolList?.get(0) ?: ""
                tvDevStackCooperation2.visibility = android.view.View.INVISIBLE
                tvDevStackCooperation3.visibility = android.view.View.INVISIBLE
            } else if (it.devToolList?.size == 2) {
                tvDevStackCooperation1.text = it.devToolList?.get(0) ?: ""
                tvDevStackCooperation2.text = it.devToolList?.get(1) ?: ""
                tvDevStackCooperation3.visibility = android.view.View.INVISIBLE
            } else if (it.devToolList?.size == 3) {
                tvDevStackCooperation1.text = it.devToolList?.get(0) ?: ""
                tvDevStackCooperation2.text = it.devToolList?.get(1) ?: ""
                tvDevStackCooperation3.text = it.devToolList?.get(2) ?: ""
            }

            if (it?.languageList?.size == 0 || it?.techStackList == null) {
                tvDevStackLanguage1.visibility = android.view.View.INVISIBLE
                tvDevStackLanguage2.visibility = android.view.View.INVISIBLE
                tvDevStackLanguage3.visibility = android.view.View.INVISIBLE
            } else if (it.languageList?.size == 1) {
                tvDevStackLanguage1.text = it.languageList?.get(0) ?: ""
                tvDevStackLanguage2.visibility = android.view.View.INVISIBLE
                tvDevStackLanguage3.visibility = android.view.View.INVISIBLE
            } else if (it.languageList?.size == 2) {
                tvDevStackLanguage1.text = it.languageList?.get(0) ?: ""
                tvDevStackLanguage2.text = it.languageList?.get(1) ?: ""
                tvDevStackLanguage3.visibility = android.view.View.INVISIBLE
            } else if (it.languageList?.size == 3) {
                tvDevStackLanguage1.text = it.languageList?.get(0) ?: ""
                tvDevStackLanguage2.text = it.languageList?.get(1) ?: ""
                tvDevStackLanguage3.text = it.languageList?.get(2) ?: ""
            }

            if (it?.techStackList?.size == 0 || it?.techStackList == null) {
                tvDevStackTech1.visibility = android.view.View.INVISIBLE
                tvDevStackTech2.visibility = android.view.View.INVISIBLE
                tvDevStackTech3.visibility = android.view.View.INVISIBLE
            } else if (it.techStackList?.size == 1) {
                tvDevStackTech1.text = it.techStackList?.get(0) ?: ""
                tvDevStackTech2.visibility = android.view.View.INVISIBLE
                tvDevStackTech3.visibility = android.view.View.INVISIBLE
            } else if (it.techStackList?.size == 2) {
                tvDevStackTech1.text = it.techStackList?.get(0) ?: ""
                tvDevStackTech2.text = it.techStackList?.get(1) ?: ""
                tvDevStackTech3.visibility = android.view.View.INVISIBLE
            } else if (it.techStackList?.size == 3) {
                tvDevStackTech1.text = it.techStackList?.get(0) ?: ""
                tvDevStackTech2.text = it.techStackList?.get(1) ?: ""
                tvDevStackTech3.text = it.techStackList?.get(2) ?: ""
            }
        }
    }
}
