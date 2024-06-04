package com.zucchini.projects.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sample.network.datastore.NetworkPreference
import com.zucchini.common.NavigationProvider
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.FragmentMypageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MypageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding get() = _binding!!
    private val viewModel by viewModels<MypageViewModel>()

    @Inject
    lateinit var navigationProvider: NavigationProvider

    @Inject
    lateinit var networkPreference: NetworkPreference

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
        collectLogoutState()
        collectWithdrawalState()
        clickLogout()
        clickWithdrawal()

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

    private fun navigateToLoginActivity() {
        val intent = navigationProvider.toLogin()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity?.finish()
    }

    private fun loadMyKakaoInfo() {
        binding.tvUserEmail.text = networkPreference.kakaoEmail
        binding.tvUserName.text = networkPreference.kakaoNickname
        binding.ivDeveloperImage.load(networkPreference.kakaoProfileImage) {
            crossfade(true)
            placeholder(R.drawable.developer_default_image)
            transformations(RoundedCornersTransformation())
        }
    }

    private fun collectLogoutState() {
        viewModel.logoutSuccess.flowWithLifecycle(lifecycle).onEach { success ->
            if (success) {
                navigateToLoginActivity()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectWithdrawalState() {
        viewModel.withdrawalSuccess.flowWithLifecycle(lifecycle).onEach { success ->
            if (success) {
                navigateToLoginActivity()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun clickLogout() {
        binding.tvLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun clickWithdrawal() {
        binding.tvWithdrawal.setOnClickListener {
            viewModel.withdrawal()
        }
    }
}
