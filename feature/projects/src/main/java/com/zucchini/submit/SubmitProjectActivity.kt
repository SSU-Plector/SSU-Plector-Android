package com.zucchini.submit

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.zucchini.data.ContentUriRequestBody
import com.zucchini.dialog.SelectCheckBoxCommonDialog
import com.zucchini.dialog.SubmitProjectDevelopersDialog
import com.zucchini.domain.model.KeywordList
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectBinding
import com.zucchini.view.setOnSingleClickListener
import com.zucchini.view.showShortToast
import timber.log.Timber

class SubmitProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitProjectBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backClickListner()
        initImageSubmitView()
        initDialogClickListener()
    }

    private fun initDialogClickListener() {
        selectProjectCategory()
        addDevelopersInfoClickListner()
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
        val categoryKoreanList = KeywordList.categoryList.map { it.keywordKorean }

        binding.tvSubmitProjectCategory.setOnClickListener {
            SelectCheckBoxCommonDialog.newInstance(
                title = "������Ʈ ī�װ�",
                description = "������Ʈ ī�װ��� �������ּ���. (�ִ� 2��)",
                confirmButtonText = getString(R.string.all_check),
                items = categoryKoreanList as ArrayList<String>,
            ).apply {
                setConfirmButtonClickListener {
                    //
                }
            }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
        }
    }

    private fun selectStack() {
        val language = KeywordList.languageList.map { it.keywordKorean }
        val techStack = KeywordList.techStackList.map { it.keywordKorean }
        val cooperationTool = KeywordList.cooperationList.map { it.keywordKorean }

        binding.tvDevStackLanguage.setOnClickListener {
            SelectCheckBoxCommonDialog.newInstance(
                title = "��� ���",
                description = "������Ʈ���� ����� �� �������ּ���.\n(�ִ� 3��)",
                confirmButtonText = getString(R.string.all_check),
                items = language as ArrayList<String>,
            ).apply {
                setConfirmButtonClickListener {
                    //
                }
            }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
        }
        binding.tvDevStackDevStack.setOnClickListener {
            SelectCheckBoxCommonDialog.newInstance(
                title = "��� ��� ����",
                description = "������Ʈ���� ����� ��� ������ �������ּ���.\n(�ִ� 3��)",
                confirmButtonText = getString(R.string.all_check),
                items = techStack as ArrayList<String>,
            ).apply {
                setConfirmButtonClickListener {
                    //
                }
            }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
        }
        binding.tvDevStackCooperation.setOnClickListener {
            SelectCheckBoxCommonDialog.newInstance(
                title = "��� ������",
                description = "������Ʈ���� ����� �������� �������ּ���.\n(�ִ� 3��)",
                confirmButtonText = getString(R.string.all_check),
                items = cooperationTool as ArrayList<String>,
            ).apply {
                setConfirmButtonClickListener {
                    //
                }
            }.showAllowingStateLoss(supportFragmentManager, "SelectCheckBoxCommonDialog")
        }
    }

    private fun addDevelopersInfoClickListner() {
        binding.tvProjectSubmitDeveloper.setOnSingleClickListener {
            SubmitProjectDevelopersDialog()
                .apply {
                    setConfirmButtonClickListener {
                        Log.d("SubmitProjectDevelopersDialog", "Confirm Button Clicked")
                    }
                }.show(supportFragmentManager, "SubmitProjectDevelopersDialog")
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
}
