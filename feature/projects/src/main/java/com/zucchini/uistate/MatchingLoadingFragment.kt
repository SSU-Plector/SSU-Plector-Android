package com.zucchini.uistate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMatchingLoadingBinding
import com.zucchini.feature.projects.databinding.FragmentMatchingResultBinding
import com.zucchini.feature.projects.databinding.FragmentSubmitProjectBinding

class MatchingLoadingFragment : Fragment() {

    private var _binding: FragmentMatchingLoadingBinding? = null
    private val binding: FragmentMatchingLoadingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

}