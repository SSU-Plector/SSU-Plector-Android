package com.zucchini.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.domain.model.Keyword
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.databinding.FragmentProjectsBinding
import com.zucchini.projects.adapter.ProjectsAdapter
import com.zucchini.projects.adapter.SearchKeywordAdapter
import com.zucchini.projects.dummy.ProjectDummyList

class ProjectsFragment : Fragment() {
    private var _binding: FragmentProjectsBinding? = null
    private val binding: FragmentProjectsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)

        initKeywordAdapter()
        initProjectsAdapter()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
