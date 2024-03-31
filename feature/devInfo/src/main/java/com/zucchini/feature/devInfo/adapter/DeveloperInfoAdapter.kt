package com.zucchini.feature.devInfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.DeveloperInfo
import com.zucchini.feature.devInfo.R
import com.zucchini.feature.devInfo.databinding.ItemDeveloperBinding
import com.zucchini.view.ItemDiffCallback

class DeveloperInfoAdapter :
    ListAdapter<DeveloperInfo, DeveloperInfoAdapter.DeveloperInfoViewHolder>(
        ItemDiffCallback<DeveloperInfo>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DeveloperInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDeveloperBinding.inflate(inflater, parent, false)
        return DeveloperInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeveloperInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DeveloperInfoViewHolder(private val binding: ItemDeveloperBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(developerInfo: DeveloperInfo) {
            binding.ivProjectProfile.setImageResource(
                developerInfo.image ?: R.drawable.developer_default_image,
            )
            binding.tvDeveloperName.text = developerInfo.name
            binding.tvDeveloperField.text = developerInfo.field
            binding.tvDeveloperGithub.text = "Github: ${developerInfo.githubId}"
            binding.tvDeveloperClicked.text = "조회수 +${developerInfo.clicked}"
        }
    }
}
