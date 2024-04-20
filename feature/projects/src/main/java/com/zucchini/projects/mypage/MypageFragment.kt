package com.zucchini.projects.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)

        navigateToWebDocs()
        navigateToKakaoOpenChat()
        navigateToSubmitForms()

        return binding.root
    }

    private fun navigateToWebDocs() {
        binding.tvTermsOfUse.setOnClickListener {
            val privacyRuleUrl = getString(R.string.service_docs_uri)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyRuleUrl))
            startActivity(intent)
        }
        binding.tvPrivacyPolicy.setOnClickListener {
            val privacyRuleUrl = getString(R.string.service_docs_uri)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyRuleUrl))
            startActivity(intent)
        }
    }

    private fun navigateToKakaoOpenChat() {
        binding.tvComplaint.setOnClickListener {
            val kakaoOpenChatUrl = getString(R.string.tv_complaint_link)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(kakaoOpenChatUrl))
            startActivity(intent)
        }
    }

    private fun navigateToSubmitForms() {
        binding.btnApplyDeveloper.setOnClickListener {
            val developerFormUri = getString(R.string.developer_form)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(developerFormUri))
            startActivity(intent)
        }
        binding.btnApplyProject.setOnClickListener {
            val projectFormUri = getString(R.string.project_form)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(projectFormUri))
            startActivity(intent)
        }
    }
}
