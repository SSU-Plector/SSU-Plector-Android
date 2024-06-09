package com.zucchini.submit

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sample.network.datastore.NetworkPreference
import com.zucchini.domain.model.SubmitDevInfo
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivitySubmitDevBinding
import com.zucchini.projects.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SubmitDevActivity @Inject constructor() : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitDevBinding
    private val viewModel by viewModels<SubmitDevViewModel>()

    @Inject
    lateinit var networkPreference: NetworkPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitDevBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserDefaultInfo()
        backToLogin()
        collectSubmitDevInfo()
        collectSubmitDevInfoSuccess()
    }

    private fun backToLogin() {
        binding.ivBackButton.setOnClickListener {
            finish()
        }
    }

    private fun getUserDefaultInfo() {
        viewModel.kakaoNickname.flowWithLifecycle(lifecycle).onEach { nickname ->
            if (nickname.isNotEmpty()) {
                binding.tvDevSubmitName.text = viewModel.kakaoNickname.value
            }
        }.launchIn(lifecycleScope)

        viewModel.kakaoImage.flowWithLifecycle(lifecycle).onEach { image ->
            if (image.isNotEmpty()) {
                binding.ivDevSubmitProfile.load(viewModel.kakaoImage.value) {
                    crossfade(true)
                    placeholder(R.drawable.developer_default_image)
                    transformations(RoundedCornersTransformation())
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectSubmitDevInfo() {
        binding.btnSubmit.setOnClickListener {
            val devGithub = binding.etGithub.text.toString()
            val devUniversity = binding.etUniversity.text.toString()
            val devMajor = binding.etMajor.text.toString()
            val devIntro = binding.etDevIntro.text.toString()
            val devStudentNumber = binding.etStudentNumber.text.toString()
            val devPart1 = binding.etPartTitle1.text.toString()
            val devPart2 = binding.etPartTitle2.text.toString()
            val kakaoId = binding.etKakaoId.text.toString()
            val devTechStack1 = binding.etDevStackTech1.text.toString()
            val devTechStack2 = binding.etDevStackTech2.text.toString()
            val devTechStack3 = binding.etDevStackTech3.text.toString()
            val devTechStackList = listOf(devTechStack1, devTechStack2, devTechStack3)
            val devLanguage1 = binding.etDevStackLanguage1.text.toString()
            val devLanguage2 = binding.etDevStackLanguage2.text.toString()
            val devLanguage3 = binding.etDevStackLanguage3.text.toString()
            val devLanguageList = listOf(devLanguage1, devLanguage2, devLanguage3)
            val devCooperation1 = binding.etDevStackCooperation1.text.toString()
            val devCooperation2 = binding.etDevStackCooperation2.text.toString()
            val devCooperation3 = binding.etDevStackCooperation3.text.toString()
            val devCooperationList = listOf(devCooperation1, devCooperation2, devCooperation3)

            val submitDevInfo = SubmitDevInfo(
                devGithub = devGithub,
                devUniversity = devUniversity,
                devMajor = devMajor,
                devIntro = devIntro,
                kakaoId = kakaoId,
                devStudentNumber = devStudentNumber,
                devPart1 = devPart1,
                devPart2 = devPart2,
                devTechStackList = devTechStackList,
                devLanguageList = devLanguageList,
                devCooperationList = devCooperationList,
            )

            viewModel.submitDev(submitDevInfo)
            viewModel.createDeveloperInfo()
        }
    }

    private fun collectSubmitDevInfoSuccess() {
        viewModel.submitDevInfoSuccess.flowWithLifecycle(lifecycle).onEach { success ->
            if (success) {
                navigateToMain()
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToMain() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
