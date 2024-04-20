package com.zucchini.projects.projects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.zucchini.feature.projects.databinding.ActivityProjectDetailBinding
import com.zucchini.projects.projects.adapter.ProjectDetailDevAdapter
import com.zucchini.projects.dummy.ProjectDetailDevDummy

class ProjectDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProjectDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDevInfoAdapter()
    }

    private fun initDevInfoAdapter() {
        val adapter = ProjectDetailDevAdapter()
        binding.rvProjectDetailDev.adapter = adapter
        binding.rvProjectDetailDev.layoutManager = GridLayoutManager(this, 3)
        adapter.submitList(ProjectDetailDevDummy.projectDetailDevList)
    }
}
