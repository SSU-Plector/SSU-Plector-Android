package com.zucchini.ai_members.designer.branding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.zucchini.ai_members.designer.AiDesignerViewModel
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentBrandingBinding
import com.zucchini.feature.projects.databinding.FragmentStartProgressMeetingBinding
import com.zucchini.uistate.UiState
import com.zucchini.view.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BrandingFragment : Fragment() {
    private var _binding: FragmentBrandingBinding? = null
    private val binding: FragmentBrandingBinding get() = _binding!!

    private val viewModel: AiDesignerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrandingBinding.inflate(inflater, container, false)

        sendProjectBrandingInfoRequest()
        collectBrandingText()

        return binding.root
    }

    private fun sendProjectBrandingInfoRequest() {
        val requestProjectInfo = binding.etBrandingDescription.text.toString()

        binding.btnSubmit.setOnClickListener {
            hideKeyboard()
            viewModel.getProjectBranding(requestProjectInfo)
        }
    }

    private fun collectBrandingText() {
        viewModel.projectBrandingText.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Initial -> {
                    // 초기 화면
                    binding.aiDesignerBrandingResult.isVisible = false
                    binding.loadingProgressBar.isVisible = false
                    binding.tvCopy.isVisible = false
                }
                is UiState.Loading -> {
                    binding.tvCopy.isVisible = false
                    binding.aiDesignerBrandingResult.isVisible = false
                    binding.loadingProgressBar.isVisible = true
                    binding.aiDesignerBrandingResult.text = getString(R.string.loading)
                }
                is UiState.Success -> {
                    binding.tvCopy.isVisible = true
                    binding.aiDesignerBrandingResult.isVisible = true
                    binding.loadingProgressBar.isVisible = false
                    binding.aiDesignerBrandingResult.text = uiState.data
                }
                is UiState.Failure -> {
                    binding.tvCopy.isVisible = false
                    binding.aiDesignerBrandingResult.isVisible = false
                    binding.loadingProgressBar.isVisible = false
                    binding.aiDesignerBrandingResult.text = getString(R.string.fail_to_branding)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}