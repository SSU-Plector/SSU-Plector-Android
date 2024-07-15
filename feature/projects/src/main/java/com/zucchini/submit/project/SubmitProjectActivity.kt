package com.zucchini.submit.project

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.zucchini.common.NavigationProvider
import com.zucchini.data.ContentUriRequestBody
import com.zucchini.dialog.SelectCheckBoxCommonDialog
import com.zucchini.domain.model.KeywordList
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectBinding
import com.zucchini.view.setOnSingleClickListener
import com.zucchini.view.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SubmitProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitProjectBinding
    private val viewModel: SubmitProjectViewModel by viewModels()
    private lateinit var submitProjectInfo: SubmitProjectInfo

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

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backClickListner()
        initImageSubmitView()
        initDialogClickListener()
        clickSubmitButton()
    }

    private fun initDialogClickListener() {
        selectProjectCategory()
        selectStack()
    }

    private fun backClickListner() {
        binding.ivBackButton.setOnClickListener {
            finish()
        }
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

                        submitProjectInfo =
                            SubmitProjectInfo(
                                projectCategoryList = selectedEnglishItems,
                            )
                        viewModel.setSubmitDevInfo(submitProjectInfo)
                        Log.d("SubmitProjectActivity", viewModel.submitDevInfo.value.toString())
                    }
                }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
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

                        submitProjectInfo =
                            SubmitProjectInfo(
                                projectLanguageList = selectedEnglishItems,
                            )
                        viewModel.setSubmitDevInfo(submitProjectInfo)
                        Log.d("SubmitProjectActivity", viewModel.submitDevInfo.value.toString())
                    }
                }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
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

                        submitProjectInfo =
                            SubmitProjectInfo(
                                projectTechStackList = selectedEnglishItems,
                            )
                        viewModel.setSubmitDevInfo(submitProjectInfo)
                        Log.d("SubmitProjectActivity", viewModel.submitDevInfo.value.toString())
                    }
                }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
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

                        submitProjectInfo =
                            SubmitProjectInfo(
                                projectCooperationList = selectedEnglishItems,
                            )
                        viewModel.setSubmitDevInfo(submitProjectInfo)
                        Log.d("SubmitProjectActivity", viewModel.submitDevInfo.value.toString())
                    }
                }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
        }
    }

    private fun processSelectedImage(uri: Uri) {
        val requestBody =
            ContentUriRequestBody(
                context = this,
                uri = uri,
            ).toFormData()

        Timber.d("Image Uri: $uri")
        Timber.d("Image Path: ${uri.path}")
        Timber.d("Request Body: $requestBody")
    }

    private fun clickSubmitButton() {
        binding.btnSubmit.setOnSingleClickListener {
            submitProjectInfo =
                SubmitProjectInfo(
                    projectName = binding.etProjectName.text.toString(),
                    imagePath = imageUri.toString(),
                    projectGithub = binding.etGithub.text.toString(),
                    projectShortIntro = binding.etProjectIntroContentShort.text.toString(),
                    projectLongIntro = binding.etProjectIntroContentLong.text.toString(),
                    projectWebLink = binding.etProjectWebLink.text.toString(),
                    projectAppLink = binding.etProjectAppLink.text.toString(),
                    projectLink = binding.etProjectInfoLink.text.toString(),
                )

            Log.d("SubmitProjectActivity", viewModel.submitDevInfo.value.toString())
            viewModel.setSubmitDevInfo(submitProjectInfo)
            startActivity(navigationProvider.toFindDev())
        }
    }
}
