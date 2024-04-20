package com.zucchini.projects.projects

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.domain.model.Keyword
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentProjectsBinding
import com.zucchini.projects.adapter.PageIndicatorAdapter
import com.zucchini.projects.dummy.ProjectDummyList
import com.zucchini.projects.projects.adapter.ProjectsAdapter
import com.zucchini.projects.projects.adapter.SearchKeywordAdapter
import kotlinx.coroutines.launch

class ProjectsFragment : Fragment() {
    private var _binding: FragmentProjectsBinding? = null
    private val binding: FragmentProjectsBinding get() = _binding!!

    private lateinit var pageIndicatorAdapter: PageIndicatorAdapter
    private val viewModel = ProjectsViewModel()

    private val totalPage = 4
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
        // observeViewModel()
        searchProject()

        return binding.root
    }

    private fun initProjectsAdapter() {
        val projectsAdapter = ProjectsAdapter()
        binding.rvProjects.layoutManager = LinearLayoutManager(context)
        binding.rvProjects.adapter = projectsAdapter
        projectsAdapter.submitList(ProjectDummyList.projectDummyList)
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
//    private fun observeViewModel() {
//        lifecycleScope.launch {
//            viewModel.searchQuery.collect { query ->
//            }
//        }
//    }

    private fun searchProject() {
        binding.ivSearch.setOnClickListener {
            val searchContents = binding.etSearchbar.text.toString()
            viewModel.setSearchQuery(searchContents)
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
