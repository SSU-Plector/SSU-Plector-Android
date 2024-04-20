package com.zucchini.projects.projects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.ProjectDetailDevInfo
import com.zucchini.feature.projects.databinding.ItemProjectDetailDevBinding
import com.zucchini.view.ItemDiffCallback

class ProjectDetailDevAdapter :
    ListAdapter<ProjectDetailDevInfo, ProjectDetailDevAdapter.ProjectDetailDevViewHolder>(
        ItemDiffCallback<ProjectDetailDevInfo>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProjectDetailDevViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectDetailDevBinding.inflate(inflater, parent, false)
        return ProjectDetailDevViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectDetailDevViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProjectDetailDevViewHolder(private val binding: ItemProjectDetailDevBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(projectDetailDevInfo: ProjectDetailDevInfo) {
            binding.run {
                tvDeveloperName.text = projectDetailDevInfo.name
                tvDeveloperInfo1.text = projectDetailDevInfo.role1
                tvDeveloperInfo2.text = projectDetailDevInfo.role2
            }
        }
    }
}
