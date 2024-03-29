package com.zucchini.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.feature.projects.databinding.FragmentProjectsBinding

class ProjectsFragment : Fragment() {
    private var _binding: FragmentProjectsBinding? = null
    private val binding: FragmentProjectsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)

        val searchKeywordAdapter = SearchKeywordAdapter()
        binding.rvSearchKeyword.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchKeyword.adapter = searchKeywordAdapter
        searchKeywordAdapter.submitList(KeywordList.searchKeyword.map { Keyword(it) })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
