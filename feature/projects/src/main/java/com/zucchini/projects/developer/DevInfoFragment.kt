package com.zucchini.projects.developer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.domain.model.Keyword
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentDevInfoBinding
import com.zucchini.projects.adapter.PageIndicatorAdapter
import com.zucchini.projects.developer.adapter.DeveloperInfoAdapter
import com.zucchini.projects.developer.viewmodel.DevInfoViewModel
import com.zucchini.projects.dummy.DeveloperInfoDummy
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

    private val totalPage = 4

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
        navigateToSubmitForms()
        collectDevelopersList()

        return binding.root
    }

    private fun collectDevelopersList() {
        viewModel.developersList
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                developerInfoAdapter.submitList(it)
                Log.d("DevInfoFragment", it.toString())
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initKeywordAdapter() {
        val searchKeywordAdapter = SearchKeywordAdapter()
        binding.rvSearchKeyword.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchKeyword.adapter = searchKeywordAdapter
        searchKeywordAdapter.submitList(KeywordList.searchKeyword.map { Keyword(it) })
    }

    private fun initDeveloperAdapter() {
        developerInfoAdapter = DeveloperInfoAdapter()
        binding.rvDevinfo.adapter = developerInfoAdapter
        binding.rvDevinfo.layoutManager = GridLayoutManager(context, 2)
    }

    private fun initPageIndicator() {
        pageIndicatorAdapter = PageIndicatorAdapter(requireContext(), totalPage)
        binding.rvPageIndicator.adapter = pageIndicatorAdapter
        binding.rvPageIndicator.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun navigateToSubmitForms() {
        binding.floatingActionButton.setOnClickListener {
            val developerFormUri = getString(R.string.developer_form)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(developerFormUri))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
