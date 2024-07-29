package com.zucchini.ai_members.matching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.activityViewModels
import com.zucchini.dialog.SelectCheckBoxCommonDialog
import com.zucchini.domain.model.projects.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentSelectMatchingConditionsBinding
import com.zucchini.view.hideKeyboard
import com.zucchini.view.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectMatchingConditionsFragment : Fragment() {

    private var _binding: FragmentSelectMatchingConditionsBinding? = null
    private val binding: FragmentSelectMatchingConditionsBinding get() = _binding!!

    private val viewModel: AiMatchingBotViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectMatchingConditionsBinding.inflate(inflater, container, false)

        setNumberPicker()
        hideKeyboardClickListeners()
        selectStacks()
        clickResultButton()
        clickProjectExperience()
        updateAdditionalConditions()

        return binding.root
    }

    private fun clickResultButton() {
        binding.btnMatchingResultNext.setOnClickListener {
            setStudentIdRange()
        }
    }

    private fun setNumberPicker() {
        binding.run {
            npMinStudentId.setUpStudentIdRange(MINTOTAL, MAXTOTAL)
            npMinStudentId.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            npMinStudentId.setOnValueChangedListener { _, _, newStudentId ->
                viewModel.updateMinStudentId(newStudentId)
            }

            npMaxStudentId.setUpStudentIdRange(MINTOTAL, MAXTOTAL)
            npMaxStudentId.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            npMaxStudentId.setOnValueChangedListener { _, _, newStudentId ->
                viewModel.updateMaxStudentId(newStudentId)
            }
        }
    }

    private fun updateAdditionalConditions() {
        val additionalConditions = binding.etMatchingDescription.text
        viewModel.updateAdditionalExperience(additionalConditions.toString())
    }
    private fun clickProjectExperience() {
        binding.cbProjectExperience.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateProjectExperience(isChecked)
        }
    }

    private fun selectStacks() {
        val languageMap = KeywordList.languageList.associateBy { it.keywordKorean }
        val techPartMap = KeywordList.partList.associateBy { it.keywordKorean }
        val techStackMap = KeywordList.techStackList.associateBy { it.keywordKorean }

        binding.tvDevPart.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = getString(R.string.tvDevPart_dialog_title),
                    description = getString(R.string.tvDevPart_dialog_description),
                    confirmButtonText = getString(R.string.all_check),
                    items = techPartMap.keys.toList() as ArrayList<String>,
                    maxSelectionCount = 1,
                ).apply {
                    setConfirmButtonClickListener { selectedItems ->
                        // 선택한 한글 키워드를 영어 키워드로 변환
                        val selectedEnglishItems =
                            selectedItems.mapNotNull { keywordKorean ->
                                techPartMap[keywordKorean]?.keywordEnglish
                            }

                        viewModel.updateDevPart(devPart = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
        binding.tvDevLanguage.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = getString(R.string.dev_language_dialog_title),
                    description = getString(R.string.dev_language_dialog_description),
                    confirmButtonText = getString(R.string.all_check),
                    items = languageMap.keys.toList() as ArrayList<String>,
                    maxSelectionCount = 3,
                ).apply {
                    setConfirmButtonClickListener { selectedItems ->
                        // 선택한 한글 키워드를 영어 키워드로 변환
                        val selectedEnglishItems =
                            selectedItems.mapNotNull { keywordKorean ->
                                languageMap[keywordKorean]?.keywordEnglish
                            }

                        viewModel.updateDevLanguage(devLanguage = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
        binding.tvDevTechStack.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = getString(R.string.dev_tech_dialog_title),
                    description = getString(R.string.dev_tech_dialog_description),
                    confirmButtonText = getString(R.string.all_check),
                    items = techStackMap.keys.toList() as ArrayList<String>,
                    maxSelectionCount = 3,
                ).apply {
                    setConfirmButtonClickListener { selectedItems ->
                        // 선택한 한글 키워드를 영어 키워드로 변환
                        val selectedEnglishItems =
                            selectedItems.mapNotNull { keywordKorean ->
                                techStackMap[keywordKorean]?.keywordEnglish
                            }

                        viewModel.updateDevStack(devStack = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
    }

    private fun hideKeyboardClickListeners() {
        binding.root.setOnClickListener{
            hideKeyboard()
        }
    }

    private fun NumberPicker.setUpStudentIdRange(minValue: Int, maxValue: Int) {
        this.minValue = minValue
        this.maxValue = maxValue
    }

    private fun setStudentIdRange() {
        if(binding.npMinStudentId.value > binding.npMaxStudentId.value) {
            showShortToast(getString(R.string.setStudentIdRange))
        } else {
            // TODO : result API
        }
    }
    companion object {
        val MINTOTAL = 11
        val MAXTOTAL = 24
    }
}