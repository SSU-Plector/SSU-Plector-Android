package com.zucchini.projects.projects.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.ProjectListInfoInList
import com.zucchini.feature.projects.databinding.ItemProjectsBinding
import com.zucchini.projects.projects.ProjectDetailActivity
import com.zucchini.view.ItemDiffCallback

class ProjectsAdapter : ListAdapter<ProjectListInfoInList, ProjectsAdapter.ProjectsViewHolder>(
    ItemDiffCallback<ProjectListInfoInList>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProjectsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectsBinding.inflate(inflater, parent, false)
        return ProjectsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // override fun getItemCount(): Int = 4

    inner class ProjectsViewHolder(private val binding: ItemProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(projectInfo: ProjectListInfoInList) {
            binding.run {
                ivProjectProfile.setImageResource(
                    projectInfo.imagePath?.toIntOrNull()
                        ?: com.zucchini.core.designsystem.R.drawable.project_profile_default,
                )
                tvProjectName.text = projectInfo.name
                tvProjectDescription.text = projectInfo.shortIntro
                tvProjectSorted.text = projectInfo.category
                tvProjectClicked.text = "조회수 +${projectInfo.hits}"
                root.setOnClickListener {
                    val intent = Intent(root.context, ProjectDetailActivity::class.java)
                    startActivity(root.context, intent, null)
                }
            }
        }
    }
}
