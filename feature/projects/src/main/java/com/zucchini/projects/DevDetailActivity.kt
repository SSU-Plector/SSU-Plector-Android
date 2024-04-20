package com.zucchini.projects

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.feature.projects.databinding.ActivityDevDetailBinding
import com.zucchini.projects.adapter.DevDetailProjectAdapter
import com.zucchini.projects.dummy.DevProjectsDummy

class DevDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDevDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initProjectAdapter()
    }

    private fun initProjectAdapter() {
        val projectAdapter = DevDetailProjectAdapter()
        binding.rvDevProject.layoutManager = LinearLayoutManager(this)
        binding.rvDevProject.adapter = projectAdapter
        projectAdapter.submitList(DevProjectsDummy.devProjectsInfoList)
    }
}