package com.zucchini.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivityLoginBinding
import com.zucchini.projects.MainActivity
import com.zucchini.projects.adapter.IntroducePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var autoScrollJob: Job
    private lateinit var introduceViewPagerAdapter: IntroducePagerAdapter
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        kakaoLogin()
        collectKakaoLogin()
        collectLoginState()
        setLoginViewPager()
    }

    private fun kakaoLogin() {
        binding.ivKakaoLogin.setOnClickListener {
            viewModel.loginWithKakaoApp(this)
        }
    }

    private fun collectKakaoLogin() {
        viewModel.kakaoLoginSuccess.flowWithLifecycle(lifecycle).onEach { success ->

            when {
                success -> Timber.d(getString(R.string.kakao_login_success))
                else -> Timber.d(getString(R.string.fail_kakao_login))
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectLoginState() {
        viewModel.isLogin.flowWithLifecycle(lifecycle).onEach { isLogin ->
            if (!isLogin && viewModel.kakaoLoginSuccess.value) {
                // 회원가입
                // TODO: navigate to 개발자 등록 페이지
            } else if (isLogin && viewModel.kakaoLoginSuccess.value) {
                // 로그인
                navigateToMain()
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToMain() {
        intent = Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }

    private fun setLoginViewPager() {
        val loginViewImageList = listOf(
            R.drawable.project_introduction_1,
            R.drawable.project_introduction_2,
            R.drawable.project_introduction_3,
            R.drawable.project_introduction_4,
            R.drawable.project_introduction_5,
        )

        introduceViewPagerAdapter = IntroducePagerAdapter(loginViewImageList)
        binding.vpIntroduce.adapter = introduceViewPagerAdapter
        binding.indicatorDots.attachTo(binding.vpIntroduce)
        startAutoScroll()
    }

    private fun startAutoScroll() {
        autoScrollJob = lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                delay(AUTO_SCROLL_DELAY)
                binding.vpIntroduce.setCurrentItem(
                    (binding.vpIntroduce.currentItem + 1) % introduceViewPagerAdapter.itemCount,
                    false,
                )
            }
        }
    }

    private fun stopAutoScroll() {
        autoScrollJob.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoScroll()
    }

    companion object {
        private const val AUTO_SCROLL_DELAY = 2000L
    }
}
