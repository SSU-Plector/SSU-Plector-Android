package com.zucchini.ai_members.designer.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.zucchini.ai_members.designer.AiDesignerViewModel
import com.zucchini.feature.projects.databinding.FragmentImageDesignBinding
import com.zucchini.uistate.UiState
import com.zucchini.view.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ImageDesignFragment : Fragment() {
    private var _binding: FragmentImageDesignBinding? = null
    private val binding: FragmentImageDesignBinding get() = _binding!!

    private val viewModel: AiDesignerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDesignBinding.inflate(inflater, container, false)

        sendProjectImageRequest()
        collectImagePath()

        return binding.root
    }

    private fun sendProjectImageRequest() {
        val requestProjectInfo = binding.etImageDescription.text.toString()

        binding.btnSubmitImage.setOnClickListener {
            hideKeyboard()
            viewModel.getProjectImage(requestProjectInfo)
        }
    }

    private fun collectImagePath() {
        viewModel.projectImagePath.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { uiState ->
                when (uiState) {
                    is UiState.Initial -> {
                        // 초기 화면
                        binding.loadingProgressBar.isVisible = false
                    }

                    is UiState.Loading -> {
                        binding.loadingProgressBar.isVisible = true
                        binding.ivAiDesignerImageResult.isVisible = false
                    }

                    is UiState.Success -> {
                        binding.loadingProgressBar.isVisible = false
                        binding.ivAiDesignerImageResult.isVisible = true
                        binding.ivAiDesignerImageResult.load(uiState.data) {
                            crossfade(true)
                        }
                    }

                    is UiState.Failure -> {
                        binding.loadingProgressBar.isVisible = false
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}