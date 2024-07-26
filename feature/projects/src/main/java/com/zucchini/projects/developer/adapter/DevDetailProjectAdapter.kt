package com.zucchini.projects.developer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zucchini.domain.model.developers.ProjectInfoInDevDetailModel
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
                ivProjectProfile.load(
                    projectList.imageLink
                        ?: com.zucchini.core.designsystem.R.drawable.project_profile_default,
                ) {
                    size(120, 120)
                }
                tvProjectName.text = projectList.name ?: ""
                tvProjectDescription.text = projectList.shortIntro ?: ""
                tvSortedProject.text = projectList.category ?: ""
                tvNavigateToProject.setOnClickListener {
                    val intent = Intent(binding.root.context, ProjectDetailActivity::class.java)
                    intent.putExtra("projectId", projectList.id)
                    startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
}
