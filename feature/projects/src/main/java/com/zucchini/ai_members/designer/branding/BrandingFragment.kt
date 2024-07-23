package com.zucchini.ai_members.designer.branding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentBrandingBinding
import com.zucchini.feature.projects.databinding.FragmentStartProgressMeetingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandingFragment : Fragment() {
    private var _binding: FragmentBrandingBinding? = null
    private val binding: FragmentBrandingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrandingBinding.inflate(inflater, container, false)
        return binding.root
    }

}