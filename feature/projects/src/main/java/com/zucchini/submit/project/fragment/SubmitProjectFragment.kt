package com.zucchini.submit.project.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.zucchini.data.ContentUriRequestBody
import com.zucchini.dialog.SelectCheckBoxCommonDialog
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentSubmitProjectBinding
import com.zucchini.submit.project.SubmitProjectActivity
import com.zucchini.submit.project.SubmitProjectViewModel
import com.zucchini.view.showShortToast
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File

class SubmitProjectFragment : Fragment() {
    private var _binding: FragmentSubmitProjectBinding? = null
    private val binding: FragmentSubmitProjectBinding get() = _binding!!

    private val viewModel: SubmitProjectViewModel by activityViewModels()

    private var imageUri = Uri.EMPTY

    private val launcher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            if (imageUri != null) {
                binding.ivProjectSubmit.load(imageUri)
                processSelectedImage(imageUri!!)
            } else {
                showShortToast(getString(R.string.submit_image))
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSubmitProjectBinding.inflate(inflater, container, false)

        initImageSubmitView()
        initDialogClickListener()
        clickSubmitButton()

        return binding.root
    }

    private fun initDialogClickListener() {
        selectProjectCategory()
        selectStack()
    }

    private fun initImageSubmitView() {
        binding.tvProjectSubmitImage.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun selectProjectCategory() {
        // 카테고리 키워드 리스트를 한글과 영어 키워드를 매핑하는 Map으로 준비
        val keywordMap = KeywordList.categoryList.associateBy { it.keywordKorean }

        binding.tvSubmitProjectCategory.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = "프로젝트 카테고리",
                    description = "프로젝트 카테고리를 선택해주세요. (최대 2개)",
                    confirmButtonText = getString(R.string.all_check),
                    items = keywordMap.keys.toList() as ArrayList<String>, // 한글 리스트 전달
                    maxSelectionCount = 2,
                ).apply {
                    setConfirmButtonClickListener { selectedItems ->
                        // 선택한 한글 키워드를 영어 키워드로 변환
                        val selectedEnglishItems =
                            selectedItems.mapNotNull { keywordKorean ->
                                keywordMap[keywordKorean]?.keywordEnglish
                            }

                        viewModel.updateProjectCategory(projectCategoryList = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
    }

    private fun selectStack() {
        val languageMap = KeywordList.languageList.associateBy { it.keywordKorean }
        val techStackMap = KeywordList.techStackList.associateBy { it.keywordKorean }
        val cooperationToolMap = KeywordList.cooperationList.associateBy { it.keywordKorean }

        binding.tvDevStackLanguage.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = getString(R.string.dialog_language_title),
                    description = getString(R.string.dialog_language_description),
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

                        viewModel.updateProjectLanguage(projectLanguageList = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
        binding.tvDevStackDevStack.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = getString(R.string.dialog_techstack_title),
                    description = getString(R.string.dialog_techstack_description),
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

                        viewModel.updateProjectTechStack(projectTechStackList = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
        binding.tvDevStackCooperation.setOnClickListener {
            SelectCheckBoxCommonDialog
                .newInstance(
                    title = getString(R.string.dialog_cooperation_title),
                    description = getString(R.string.dialog_cooperation_description),
                    confirmButtonText = getString(R.string.all_check),
                    items = cooperationToolMap.keys.toList() as ArrayList<String>,
                    maxSelectionCount = 3,
                ).apply {
                    setConfirmButtonClickListener { selectedItems ->
                        // 선택한 한글 키워드를 영어 키워드로 변환
                        val selectedEnglishItems =
                            selectedItems.mapNotNull { keywordKorean ->
                                cooperationToolMap[keywordKorean]?.keywordEnglish
                            }

                        viewModel.updateProjectCooperation(projectCooperationList = selectedEnglishItems)
                    }
                }.showAllowingStateLoss(parentFragmentManager, "SelectCheckBoxCommonDialog")
        }
    }

    private fun processSelectedImage(uri: Uri) {
        val requestBody =
            ContentUriRequestBody(
                context = requireContext(),
                uri = uri,
            ).toFormData()

        Timber.d("Image Uri: $uri")
        Timber.d("Image Path: ${uri.path}")
        Timber.d("Request Body: $requestBody")
    }

    private fun clickSubmitButton() {
        binding.btnNext.setOnClickListener {
            viewModel.updateProjectInfo(
                projectName = binding.etProjectName.text.toString(),
                projectGithub = binding.etGithub.text.toString(),
                projectShortIntro = binding.etProjectIntroContentShort.text.toString(),
                projectLongIntro = binding.etProjectIntroContentLong.text.toString(),
                projectWebLink = binding.etProjectWebLink.text.toString(),
                projectAppLink = binding.etProjectAppLink.text.toString(),
                projectLink = binding.etProjectInfoLink.text.toString(),
            )
            (activity as? SubmitProjectActivity)?.setCurrentItem(1)
        }
    }

}
