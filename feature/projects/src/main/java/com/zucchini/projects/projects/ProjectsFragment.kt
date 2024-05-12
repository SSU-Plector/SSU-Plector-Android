package com.zucchini.projects.projects

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.domain.model.Keyword
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentProjectsBinding
import com.zucchini.projects.adapter.PageIndicatorAdapter
import com.zucchini.projects.projects.adapter.ProjectsAdapter
import com.zucchini.projects.projects.adapter.SearchKeywordAdapter
import com.zucchini.projects.projects.viewmodel.ProjectsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProjectsFragment : Fragment() {
    private var _binding: FragmentProjectsBinding? = null
    private val binding: FragmentProjectsBinding get() = _binding!!

    private lateinit var pageIndicatorAdapter: PageIndicatorAdapter

    private val totalPage = 4

    private val viewModel by viewModels<ProjectsListViewModel>()
    private val projectsAdapter = ProjectsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)

        initKeywordAdapter()
        initProjectsAdapter()
        initPageIndicator()
        setSortingKeyword()
        navigateToSubmitForms()
        collectProjectList()

        return binding.root
    }

    private fun collectProjectList() {
        viewModel.projectsList
            .flowWithLifecycle(lifecycle)
            .onEach { projectList ->
                projectsAdapter.submitList(projectList)
            }.launchIn(lifecycleScope)
    }

    private fun initProjectsAdapter() {
        binding.rvProjects.layoutManager = LinearLayoutManager(context)
        binding.rvProjects.adapter = projectsAdapter
    }

    private fun initKeywordAdapter() {
        val searchKeywordAdapter = SearchKeywordAdapter()
        binding.rvSearchKeyword.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchKeyword.adapter = searchKeywordAdapter
        searchKeywordAdapter.submitList(KeywordList.searchKeyword.map { Keyword(it) })
    }

    private fun initPageIndicator() {
        pageIndicatorAdapter = PageIndicatorAdapter(requireContext(), totalPage)
        binding.rvPageIndicator.adapter = pageIndicatorAdapter
        binding.rvPageIndicator.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun navigateToSubmitForms() {
        binding.floatingActionButton.setOnClickListener {
            val projectFormUri = getString(R.string.project_form)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(projectFormUri))
            startActivity(intent)
        }
    }

    private fun setSortingKeyword() {
        binding.tvSortRecent.setOnClickListener {
            binding.tvSortRecent.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.olive_black,
                ),
            )
            binding.tvSortHighCheck.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.gray1,
                ),
            )
            binding.tvSortLowCheck.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.gray1,
                ),
            )
        }
        binding.tvSortHighCheck.setOnClickListener {
            binding.tvSortRecent.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.gray1,
                ),
            )
            binding.tvSortHighCheck.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.olive_black,
                ),
            )
            binding.tvSortLowCheck.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.gray1,
                ),
            )
        }
        binding.tvSortLowCheck.setOnClickListener {
            binding.tvSortRecent.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.gray1,
                ),
            )
            binding.tvSortHighCheck.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.gray1,
                ),
            )
            binding.tvSortLowCheck.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.zucchini.core.designsystem.R.color.olive_black,
                ),
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
