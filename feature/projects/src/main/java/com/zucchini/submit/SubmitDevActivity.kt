package com.zucchini.submit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zucchini.domain.model.SubmitDevInfo
import com.zucchini.feature.projects.databinding.ActivitySubmitDevBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubmitDevActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitDevBinding
    private val viewModel by viewModels<SubmitDevViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitDevBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backToLogin()
        collectSubmitDevInfo()
    }

    private fun backToLogin() {
        binding.ivBackButton.setOnClickListener {
            finish()
        }
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
                devGithub,
                devUniversity,
                devMajor,
                devIntro,
                devStudentNumber,
                devPart1,
                devPart2,
                devTechStackList,
                devLanguageList,
                devCooperationList,
            )

            viewModel.submitDev(submitDevInfo)
            viewModel.createDeveloperInfo()
        }
    }
}
