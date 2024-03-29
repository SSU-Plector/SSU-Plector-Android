package com.zucchini.feature.devInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.feature.devInfo.adapter.DeveloperInfoAdapter
import com.zucchini.feature.devInfo.databinding.FragmentDevInfoBinding

class DevInfoFragment : Fragment() {
    private var _binding: FragmentDevInfoBinding? = null
    private val binding: FragmentDevInfoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDevInfoBinding.inflate(inflater, container, false)

        val developerInfoAdapter = DeveloperInfoAdapter()
        binding.rvDevinfo.adapter = developerInfoAdapter
        binding.rvDevinfo.layoutManager = GridLayoutManager(context, 2)
        developerInfoAdapter.submitList(DeveloperInfoDummy.developerInfoList)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
