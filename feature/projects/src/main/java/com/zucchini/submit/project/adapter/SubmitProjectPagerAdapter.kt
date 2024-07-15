package com.zucchini.submit.project.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zucchini.submit.project.fragment.SubmitProjectFragment
import com.zucchini.submit.project.fragment.SubmitProjectWithDevFragment

class SubmitProjectPagerAdapter(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SubmitProjectFragment()
            1 -> SubmitProjectWithDevFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
