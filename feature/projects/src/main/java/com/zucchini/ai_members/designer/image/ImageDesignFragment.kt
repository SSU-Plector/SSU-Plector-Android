package com.zucchini.ai_members.designer.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zucchini.feature.projects.databinding.FragmentImageDesignBinding
import com.zucchini.feature.projects.databinding.FragmentStartProgressMeetingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDesignFragment : Fragment() {
    private var _binding: FragmentImageDesignBinding? = null
    private val binding: FragmentImageDesignBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDesignBinding.inflate(inflater, container, false)
        return binding.root
    }


}