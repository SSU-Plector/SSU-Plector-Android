package com.zucchini.ai_members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zucchini.common.NavigationProvider
import com.zucchini.feature.projects.databinding.FragmentAiMembersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AiMembersFragment : Fragment() {
    private var _binding: FragmentAiMembersBinding? = null
    private val binding: FragmentAiMembersBinding get() = _binding!!

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAiMembersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToAiMembers()
    }

    private fun navigateToAiMembers() {
        binding.aiMembersPmButton.setOnClickListener {
            startActivity(navigationProvider.toAiPmMember())
        }

        binding.aiMembersDesignerButton.setOnClickListener {
            startActivity(navigationProvider.toAiDesignerMembers())
        }
    }
}
