package com.zucchini.projects.developer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.DeveloperInfo
import com.zucchini.feature.projects.R
import com.zucchini.feature.projects.databinding.ItemDeveloperBinding
import com.zucchini.projects.developer.DevDetailActivity
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
            binding.run {
                ivProjectProfile.setImageResource(
                    developerInfo.image
                        ?: R.drawable.developer_default_image,
                )
                tvDeveloperName.text = developerInfo.name
                tvDeveloperField.text = developerInfo.field
                tvDeveloperGithub.text = "Github: ${developerInfo.githubId}"
                tvDeveloperClicked.text = "조회수 +${developerInfo.clicked}"

                root.setOnClickListener {
                    val intent = Intent(binding.root.context, DevDetailActivity::class.java)
                    startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
}
