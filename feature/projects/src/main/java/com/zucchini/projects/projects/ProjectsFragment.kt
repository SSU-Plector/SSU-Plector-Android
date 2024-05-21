package com.zucchini.projects.projects

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
import com.zucchini.domain.model.SortOption
import com.zucchini.feature.projects.databinding.FragmentProjectsBinding
import com.zucchini.projects.adapter.PageIndicatorAdapter
import com.zucchini.projects.projects.adapter.ProjectsAdapter
import com.zucchini.projects.projects.viewmodel.ProjectsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProjectsFragment : Fragment() {
    private var _binding: FragmentProjectsBinding? = null
    private val binding: FragmentProjectsBinding get() = _binding!!

    private val viewModel by viewModels<ProjectsListViewModel>()
    private val projectsAdapter = ProjectsAdapter()
    private lateinit var pageIndicatorAdapter: PageIndicatorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)

        initProjectsAdapter()
        initSortingKeywords()
        collectProjectList()
        searchWithSearchString()
        observePageChanges()
        initPageIndicator()

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
        binding.rvProjects.isNestedScrollingEnabled = false
    }

    private fun initSortingKeywords() {
        binding.tvSortRecent.setOnClickListener {
            viewModel.updateSortOption(SortOption.RECENT)
            updateSortKeywordColors(SortOption.RECENT)
        }
        binding.tvSortHighCheck.setOnClickListener {
            viewModel.updateSortOption(SortOption.HIGH)
            updateSortKeywordColors(SortOption.HIGH)
        }
        binding.tvSortLowCheck.setOnClickListener {
            viewModel.updateSortOption(SortOption.LOW)
            updateSortKeywordColors(SortOption.LOW)
        }
    }

    private fun updateSortKeywordColors(selectedOption: SortOption) {
        val activeColor = ContextCompat.getColor(
            requireContext(),
            com.zucchini.core.designsystem.R.color.olive_black,
        )
        val inactiveColor =
            ContextCompat.getColor(requireContext(), com.zucchini.core.designsystem.R.color.gray1)

        binding.tvSortRecent.setTextColor(if (selectedOption == SortOption.RECENT) activeColor else inactiveColor)
        binding.tvSortHighCheck.setTextColor(if (selectedOption == SortOption.HIGH) activeColor else inactiveColor)
        binding.tvSortLowCheck.setTextColor(if (selectedOption == SortOption.LOW) activeColor else inactiveColor)
    }

    private fun searchWithSearchString() {
        binding.ivSearch.setOnClickListener {
            val searchString = binding.etSearchbar.text.toString()
            viewModel.updateSearchString(searchString)
        }
    }

    private fun observePageChanges() {
        viewModel.page
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { page ->
                pageIndicatorAdapter.setCurrentPage(page)
                viewModel.getProjectsListData(page = page)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initPageIndicator() {
        pageIndicatorAdapter =
            PageIndicatorAdapter(requireContext(), viewModel.totalPage.value) { page ->
                viewModel.updatePage(page)
            }
        binding.rvPageIndicator.adapter = pageIndicatorAdapter
        binding.rvPageIndicator.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
