package com.zucchini.projects.developer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.databinding.FragmentDevInfoBinding
import com.zucchini.projects.adapter.PageIndicatorAdapter
import com.zucchini.projects.developer.adapter.DeveloperInfoAdapter
import com.zucchini.projects.developer.viewmodel.DevInfoViewModel
import com.zucchini.projects.projects.adapter.SearchKeywordAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DevInfoFragment : Fragment() {
    private var _binding: FragmentDevInfoBinding? = null
    private val binding: FragmentDevInfoBinding get() = _binding!!

    private lateinit var developerInfoAdapter: DeveloperInfoAdapter
    private lateinit var pageIndicatorAdapter: PageIndicatorAdapter

    private val viewModel by viewModels<DevInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDevInfoBinding.inflate(inflater, container, false)

        initKeywordAdapter()
        initDeveloperAdapter()
        initPageIndicator()
        collectDevelopersList()
        observePageChanges()

        return binding.root
    }

    private fun collectDevelopersList() {
        viewModel.developersList
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                developerInfoAdapter.submitList(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initKeywordAdapter() {
        val searchKeywordAdapter = SearchKeywordAdapter(
            onKeywordClick = { part ->
                viewModel.updatePart(part?.keywordEnglish)
            },
        )
        binding.rvSearchKeyword.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchKeyword.adapter = searchKeywordAdapter
        searchKeywordAdapter.submitList(KeywordList.partList)
    }

    private fun initDeveloperAdapter() {
        developerInfoAdapter = DeveloperInfoAdapter()
        binding.run {
            rvDevinfo.layoutManager = GridLayoutManager(context, 2)
            rvDevinfo.adapter = developerInfoAdapter
            rvDevinfo.isNestedScrollingEnabled = false
        }
    }

    private fun observePageChanges() {
        viewModel.page
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { page ->
                pageIndicatorAdapter.setCurrentPage(page)
                viewModel.getDevelopersListData(page = page)
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
            rvDevinfo.isNestedScrollingEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
