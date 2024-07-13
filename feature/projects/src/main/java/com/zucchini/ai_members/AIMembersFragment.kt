package com.zucchini.ai_members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zucchini.feature.projects.databinding.FragmentAiMembersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AIMembersFragment : Fragment() {
    private var _binding: FragmentAiMembersBinding? = null
    private val binding: FragmentAiMembersBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAiMembersBinding.inflate(inflater, container, false)

        return binding.root
    }
}
