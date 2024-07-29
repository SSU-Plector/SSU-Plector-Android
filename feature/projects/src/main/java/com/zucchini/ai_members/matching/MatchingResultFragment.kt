package com.zucchini.ai_members.matching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMatchingResultBinding
import com.zucchini.feature.projects.databinding.FragmentSelectMatchingConditionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchingResultFragment : Fragment() {

    private var _binding: FragmentMatchingResultBinding? = null
    private val binding: FragmentMatchingResultBinding get() = _binding!!

    private val viewModel: AiMatchingBotViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingResultBinding.inflate(inflater, container, false)
        return binding.root
    }
}