package com.zucchini.submit.project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.common.NavigationProvider
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentSubmitProjectWithDevBinding
import com.zucchini.submit.project.SubmitProjectViewModel
import com.zucchini.submit.project.adapter.FindDevAdapter
import com.zucchini.view.showShortToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SubmitProjectWithDevFragment : Fragment() {
    private var _binding: FragmentSubmitProjectWithDevBinding? = null
    private val binding: FragmentSubmitProjectWithDevBinding get() = _binding!!

    private val viewModel: SubmitProjectViewModel by activityViewModels()
    private lateinit var devAdapter: FindDevAdapter

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSubmitProjectWithDevBinding.inflate(inflater, container, false)

        initDevInfoAdapter()
        clickSearchKeyword()
        clickSubmitButton()

        return binding.root
    }

    private fun initDevInfoAdapter() {
        devAdapter = FindDevAdapter(viewModel)
        binding.rvFindDev.adapter = devAdapter
        binding.rvFindDev.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun clickSearchKeyword() {
        binding.ivSearch.setOnClickListener {
            viewModel.searchDeveloperList(binding.etSearchbar.text.toString())
        }

        viewModel.searchDeveloperResultList.flowWithLifecycle(lifecycle).onEach { developerList ->
            devAdapter.submitList(developerList)
        }.launchIn(lifecycleScope)
    }

    private fun clickSubmitButton() {
        binding.btnNext.setOnClickListener {
            viewModel.submitProject()
            viewModel.isSuccessSubmitProject.flowWithLifecycle(lifecycle).onEach { isSuccess ->
                if (isSuccess) {
                    startActivity(navigationProvider.toMain())
                } else {
                    showShortToast(getString(R.string.fail_submit_project))
                }
            }.launchIn(lifecycleScope)
        }
    }
}
