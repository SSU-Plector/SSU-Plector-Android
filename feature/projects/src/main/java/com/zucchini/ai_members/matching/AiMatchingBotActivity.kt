package com.zucchini.ai_members.matching

import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.zucchini.ai_members.designer.branding.BrandingFragment
import com.zucchini.common.NavigationProvider
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivityAiMatchingBotBinding
import com.zucchini.uistate.MatchingLoadingFragment
import com.zucchini.uistate.UiState
import com.zucchini.view.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class AiMatchingBotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAiMatchingBotBinding
    private val viewModel by viewModels<AiMatchingBotViewModel>()

    override fun onResume() {
        super.onResume()
        initFragmentView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAiMatchingBotBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFragmentView()

        collectMatchingResult()

    }

    private fun initFragmentView() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.vpMatchingBot.id, SelectMatchingConditionsFragment())
            .commit()
    }

    private fun collectMatchingResult() {
        viewModel.resultSuccess.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Initial -> {
                    // 초기 화면
                }
                is UiState.Loading -> {
                    // 로딩 화면
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.vpMatchingBot.id, MatchingLoadingFragment())
                        .commit()
                }
                is UiState.Success -> {
                    // 매칭 결과 화면
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.vpMatchingBot.id, MatchingResultFragment())
                        .commit()
                }
                is UiState.Failure -> {
                    showShortToast(getString(R.string.fail_to_matching))
                }
            }


        }.launchIn(lifecycleScope)
    }

}