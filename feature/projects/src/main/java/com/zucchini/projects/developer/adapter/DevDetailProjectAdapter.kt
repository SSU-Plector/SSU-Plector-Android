package com.zucchini.projects.developer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.DevDetailProjectInfo
import com.zucchini.domain.model.ProjectInfo
import com.zucchini.domain.model.ProjectInfoInDevDetailModel
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ItemDevDetailProjectsBinding
import com.zucchini.projects.projects.ProjectDetailActivity
import com.zucchini.view.ItemDiffCallback

class DevDetailProjectAdapter :
    ListAdapter<ProjectInfoInDevDetailModel, DevDetailProjectAdapter.DevDetailProjectInfoViewHolder>(
        ItemDiffCallback<ProjectInfoInDevDetailModel>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DevDetailProjectInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDevDetailProjectsBinding.inflate(inflater, parent, false)
        return DevDetailProjectInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DevDetailProjectInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DevDetailProjectInfoViewHolder(private val binding: ItemDevDetailProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(projectList: ProjectInfoInDevDetailModel) {
            binding.run {
                ivProjectProfile.setImageResource(
                    projectList.imageLink?.toIntOrNull() ?: R.drawable.developer_default_image,
                )
                tvProjectName.text = projectList.name ?: ""
                tvProjectDescription.text = projectList.shortIntro ?: ""
                tvSortedProject.text = projectList.category ?: "SERVICE"
                tvNavigateToProject.setOnClickListener {
                    val intent = Intent(binding.root.context, ProjectDetailActivity::class.java)
                    startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
}
