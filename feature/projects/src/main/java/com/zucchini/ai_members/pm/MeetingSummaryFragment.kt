package com.zucchini.ai_members.pm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zucchini.feature.projects.databinding.FragmentMeetingSummaryBinding

class MeetingSummaryFragment : Fragment() {
    private var _binding: FragmentMeetingSummaryBinding? = null
    private val binding: FragmentMeetingSummaryBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMeetingSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }
}
