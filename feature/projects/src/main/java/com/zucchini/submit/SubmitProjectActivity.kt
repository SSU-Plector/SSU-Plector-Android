package com.zucchini.submit

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.zucchini.data.ContentUriRequestBody
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivitySubmitProjectBinding
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
        binding.tvSubmitProjectCategory.setOnClickListener {
            // TODO: Implement category selection
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
