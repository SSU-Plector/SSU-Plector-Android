package com.zucchini.submit.project.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zucchini.dialog.SelectCheckBoxCommonDialog
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentSubmitProjectBinding
import com.zucchini.submit.project.SubmitProjectActivity
import com.zucchini.submit.project.SubmitProjectViewModel

class SubmitProjectFragment : Fragment() {
    private var _binding: FragmentSubmitProjectBinding? = null
    private val binding: FragmentSubmitProjectBinding get() = _binding!!

    private val viewModel: SubmitProjectViewModel by activityViewModels()

    // 권한 요청
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navigateToGallery()
            }
        }

    // 가져온 사진 보여주기
    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleImageResult(result)
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

    private fun initImageSubmitView() {
        binding.tvProjectSubmitImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                navigateToGallery()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun handleImageResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                // 이미지 URI를 사용하여 이미지를 표시하거나 추가 작업을 수행합니다.
                binding.ivProjectSubmit.setImageURI(uri)
                val uriToAbsolutePath = absolutelyPath(uri, requireContext())
                viewModel.updateImagePath(uriToAbsolutePath)
                Log.d("Selected Image", "Selected Image URI: $uri")
            }
        } else {
            Log.d("Image Failure", "Image selection failed or was canceled.")
        }
    }

    private fun absolutelyPath(
        path: Uri?,
        context: Context,
    ): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        val result = c?.getString(index!!)

        return result!!
    }

    private fun navigateToGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }

    private fun initDialogClickListener() {
        selectProjectCategory()
        selectStacks()
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

    private fun selectStacks() {
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
