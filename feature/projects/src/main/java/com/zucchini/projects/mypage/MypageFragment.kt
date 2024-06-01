package com.zucchini.projects.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMypageBinding
import com.zucchini.projects.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)

        navigateToWebDocs()
        navigateToKakaoOpenChat()
        navigateToMyDevInfo()
        loadMyKakaoInfo()

        return binding.root
    }

    private fun navigateToWebDocs() {
        binding.tvTermsOfUse.setOnClickListener {
            val serviceUsePolicy = getString(R.string.service_use_docs_uri)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(serviceUsePolicy))
            startActivity(intent)
        }
        binding.tvPrivacyPolicy.setOnClickListener {
            val privacyRuleUrl = getString(R.string.privacy_docs_uri)
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

    private fun navigateToMyDevInfo() {
        binding.tvNavigateToMyInfo.setOnClickListener {
            // TODO 개발자 상세정보로 이동
        }
    }

    private fun loadMyKakaoInfo() {
        mainViewModel.userEmail.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { email ->
                binding.tvUserEmail.text = email
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        mainViewModel.userNickname.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { nickname ->
                binding.tvUserName.text = nickname
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        mainViewModel.userProfile.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { image ->
                binding.ivDeveloperImage.load(image) {
                    crossfade(true)
                    placeholder(R.drawable.developer_default_image)
                    transformations(RoundedCornersTransformation())
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
