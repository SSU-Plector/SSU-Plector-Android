package com.zucchini.projects

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ActivityIntroduceBinding
import com.zucchini.projects.adapter.IntroducePagerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroduceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroduceBinding

    private lateinit var autoScrollJob: Job
    private lateinit var introduceViewPagerAdapter: IntroducePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navigateToMain()
        setLoginViewPager()
    }

    private fun navigateToMain() {
        binding.root.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    private fun setLoginViewPager() {
        val loginViewImageList = listOf(
            R.drawable.project_introduction_1,
            R.drawable.project_introduction_2,
            R.drawable.project_introduction_3,
            R.drawable.project_introduction_4,
            R.drawable.project_introduction_5
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
