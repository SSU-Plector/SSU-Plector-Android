package com.zucchini.projects.projects.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.feature.projects.databinding.ItemProjectDetailDevBinding
import com.zucchini.projects.developer.DevDetailActivity
import com.zucchini.projects.projects.ProjectDetailActivity
import com.zucchini.view.ItemDiffCallback

class ProjectDetailDevAdapter :
    ListAdapter<ProjectsDetailModel.DeveloperListInProjectDetail, ProjectDetailDevAdapter.ProjectDetailDevViewHolder>(
        ItemDiffCallback<ProjectsDetailModel.DeveloperListInProjectDetail>(
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
        fun bind(developerListInProjectDetail: ProjectsDetailModel.DeveloperListInProjectDetail) {
            binding.run {
                tvDeveloperName.text = developerListInProjectDetail.name
                if (developerListInProjectDetail.partList.size == 1) {
                    tvDeveloperInfo1.text = developerListInProjectDetail.partList[0] ?: ""
                    tvDeveloperInfo2.visibility = android.view.View.GONE
                } else {
                    tvDeveloperInfo1.text = developerListInProjectDetail.partList[0] ?: ""
                    tvDeveloperInfo2.text = developerListInProjectDetail.partList[1] ?: ""
                }

                root.setOnClickListener {
                    val intent = Intent(binding.root.context, DevDetailActivity::class.java)
                    intent.putExtra("developerId", developerListInProjectDetail.id)
                    ContextCompat.startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
}
