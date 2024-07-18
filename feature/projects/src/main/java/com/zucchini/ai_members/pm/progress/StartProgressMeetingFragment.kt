package com.zucchini.ai_members.pm.progress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.zucchini.ai_members.pm.AiPmViewModel
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentStartProgressMeetingBinding

class StartProgressMeetingFragment : Fragment() {
    private var _binding: FragmentStartProgressMeetingBinding? = null
    private val binding: FragmentStartProgressMeetingBinding get() = _binding!!

    private val viewModel: AiPmViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartProgressMeetingBinding.inflate(inflater, container, false)

        return binding.root
    }



}