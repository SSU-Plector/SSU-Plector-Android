package com.zucchini.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.feature.projects.databinding.FragmentDevInfoBinding
import com.zucchini.projects.adapter.DeveloperInfoAdapter
import com.zucchini.projects.dummy.DeveloperInfoDummy

class DevInfoFragment : Fragment() {
    private var _binding: FragmentDevInfoBinding? = null
    private val binding: FragmentDevInfoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDevInfoBinding.inflate(inflater, container, false)

        initDeveloperAdapter()

        return binding.root
    }

    private fun initDeveloperAdapter() {
        val developerInfoAdapter = DeveloperInfoAdapter()
        binding.rvDevinfo.adapter = developerInfoAdapter
        binding.rvDevinfo.layoutManager = GridLayoutManager(context, 2)
        developerInfoAdapter.submitList(DeveloperInfoDummy.developerInfoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
