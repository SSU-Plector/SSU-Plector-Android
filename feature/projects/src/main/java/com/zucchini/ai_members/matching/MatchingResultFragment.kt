package com.zucchini.ai_members.matching

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMatchingResultBinding
import com.zucchini.feature.projects.databinding.FragmentSelectMatchingConditionsBinding
import com.zucchini.projects.developer.DevDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        collectMatchingResult()
        return binding.root
    }

    private fun collectMatchingResult() {
        viewModel.matchedDeveloperList.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {

            binding.tvDeveloperName1.text = it.getOrNull(0)?.name
            binding.tvDeveloperEmail1.text = it.getOrNull(0)?.email
            binding.tvDeveloper1Part1.text = it.getOrNull(0)?.part1
            binding.tvDeveloper1Part2.text = it.getOrNull(0)?.part2
            binding.tvDeveloperDescription1.text = it.getOrNull(0)?.description

            binding.tvDeveloperName2.text = it.getOrNull(1)?.name
            binding.tvDeveloperEmail2.text = it.getOrNull(1)?.email
            binding.tvDeveloper2Part1.text = it.getOrNull(1)?.part1
            binding.tvDeveloper2Part2.text = it.getOrNull(1)?.part2
            binding.tvDeveloperDescription2.text = it.getOrNull(1)?.description

            binding.tvDeveloperName3.text = it.getOrNull(2)?.name
            binding.tvDeveloperEmail3.text = it.getOrNull(2)?.email
            binding.tvDeveloper3Part1.text = it.getOrNull(2)?.part1
            binding.tvDeveloper3Part2.text = it.getOrNull(2)?.part2
            binding.tvDeveloperDescription3.text = it.getOrNull(2)?.description

            val devInfo1 = it.getOrNull(0)?.id
            val devInfo2 = it.getOrNull(1)?.id
            val devInfo3 = it.getOrNull(2)?.id

            binding.clMatchingResultDevInfo1.setOnClickListener {
                val intent = Intent(binding.root.context, DevDetailActivity::class.java)
                intent.putExtra("developerId", devInfo1)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }

            binding.clMatchingResultDevInfo2.setOnClickListener {
                val intent = Intent(binding.root.context, DevDetailActivity::class.java)
                intent.putExtra("developerId", devInfo2)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }

            binding.clMatchingResultDevInfo3.setOnClickListener {
                val intent = Intent(binding.root.context, DevDetailActivity::class.java)
                intent.putExtra("developerId", devInfo3)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}