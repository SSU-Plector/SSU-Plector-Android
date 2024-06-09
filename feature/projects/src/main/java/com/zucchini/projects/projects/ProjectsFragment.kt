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
import com.zucchini.core.designsystem.R
import com.zucchini.domain.model.KeywordList
import com.zucchini.domain.model.SortOption
import com.zucchini.feature.projects.databinding.FragmentProjectsBinding
import com.zucchini.projects.adapter.PageIndicatorAdapter
import com.zucchini.projects.projects.adapter.ProjectsAdapter
import com.zucchini.projects.projects.adapter.SearchKeywordAdapter
import com.zucchini.projects.projects.viewmodel.ProjectsListViewModel
import com.zucchini.view.hideKeyboard
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

        binding.root.setOnClickListener {
            hideKeyboard()
        }

        initProjectsAdapter()
        initSortingKeywords()
        initKeywordAdapter()
        initPageIndicator()
        collectPageState()
        collectProjectList()
        searchWithSearchString()
        initSubmitProjectButton()

        return binding.root
    }

    private fun collectProjectList() {
        viewModel.projectsList
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { projectList ->
                projectsAdapter.submitList(projectList)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initProjectsAdapter() {
        binding.run {
            rvProjects.layoutManager = LinearLayoutManager(context)
            rvProjects.adapter = projectsAdapter
            rvProjects.isNestedScrollingEnabled = false
        }
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
            R.color.olive_black,
        )
        val inactiveColor =
            ContextCompat.getColor(requireContext(), R.color.gray1)

        binding.run {
            tvSortRecent.setTextColor(if (selectedOption == SortOption.RECENT) activeColor else inactiveColor)
            tvSortHighCheck.setTextColor(if (selectedOption == SortOption.HIGH) activeColor else inactiveColor)
            tvSortLowCheck.setTextColor(if (selectedOption == SortOption.LOW) activeColor else inactiveColor)
        }
    }

    private fun searchWithSearchString() {
        binding.ivSearch.setOnClickListener {
            val searchString = binding.etSearchbar.text.toString()
            viewModel.updateSearchString(searchString)
            hideKeyboard()
        }
    }

    private fun collectPageState() {
        viewModel.page
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { page ->
                pageIndicatorAdapter.setCurrentPage(page)
                viewModel.getProjectsListData(page = page)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.totalPage
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { totalPage ->
                if (::pageIndicatorAdapter.isInitialized) {
                    pageIndicatorAdapter.updateTotalPages(totalPage)
                } else {
                    initPageIndicator(totalPage)
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initPageIndicator(totalPages: Int = 0) {
        pageIndicatorAdapter =
            PageIndicatorAdapter(requireContext(), totalPages) { page ->
                viewModel.updatePage(page)
            }
        binding.run {
            rvPageIndicator.adapter = pageIndicatorAdapter
            rvPageIndicator.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvProjects.isNestedScrollingEnabled = false
        }
    }

    private fun initKeywordAdapter() {
        val searchKeywordAdapter = SearchKeywordAdapter {
            viewModel.updateCategory(it?.keywordEnglish)
        }
        binding.rvSearchKeyword.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchKeyword.adapter = searchKeywordAdapter
        searchKeywordAdapter.submitList(KeywordList.searchKeyword)
    }


    private fun initSubmitProjectButton() {
        binding.floatingActionButton.setOnClickListener {
            val projectFormUri = getString(com.zucchini.feature.projects.R.string.project_form)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(projectFormUri))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
