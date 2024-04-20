package com.zucchini.projects.developer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.DevDetailProjectInfo
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ItemDevDetailProjectsBinding
import com.zucchini.projects.projects.ProjectDetailActivity
import com.zucchini.view.ItemDiffCallback

class DevDetailProjectAdapter :
    ListAdapter<DevDetailProjectInfo, DevDetailProjectAdapter.DevDetailProjectInfoViewHolder>(
        ItemDiffCallback<DevDetailProjectInfo>(
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
        fun bind(devDetailProjectInfo: DevDetailProjectInfo) {
            binding.run {
                ivProjectProfile.setImageResource(
                    devDetailProjectInfo.image ?: R.drawable.developer_default_image,
                )
                tvProjectName.text = devDetailProjectInfo.name
                tvProjectDescription.text = devDetailProjectInfo.description
                tvSortedProject.text = devDetailProjectInfo.sorted
                tvNavigateToProject.setOnClickListener {
                    val intent = Intent(binding.root.context, ProjectDetailActivity::class.java)
                    startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
}
