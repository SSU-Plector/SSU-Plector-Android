package com.zucchini.projects.developer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zucchini.domain.model.DeveloperDetailInfoInListModel
import com.zucchini.feature.projects.databinding.ItemDeveloperBinding
import com.zucchini.projects.developer.DevDetailActivity
import com.zucchini.view.ItemDiffCallback

class DeveloperInfoAdapter :
    ListAdapter<DeveloperDetailInfoInListModel, DeveloperInfoAdapter.DeveloperInfoViewHolder>(
        ItemDiffCallback<DeveloperDetailInfoInListModel>(
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
        fun bind(developersInfoInList: DeveloperDetailInfoInListModel) {
            binding.run {
                ivProjectProfile.load(
                    developersInfoInList.imageLink
                        ?: com.zucchini.core.designsystem.R.drawable.project_profile_default,
                ) {
                    crossfade(true)
                    placeholder(com.zucchini.core.designsystem.R.drawable.project_profile_default)
                }
                tvDeveloperName.text = developersInfoInList.name
                tvDeveloperField.text =
                    "${developersInfoInList.part1}\n${developersInfoInList.part2}"
                tvDeveloperGithub.text = "Github: ${developersInfoInList.githubLink}"
                tvDeveloperClicked.text = "조회수 +${developersInfoInList.hits}"

                root.setOnClickListener {
                    val intent = Intent(binding.root.context, DevDetailActivity::class.java)
                    intent.putExtra("developerId", developersInfoInList.id)
                    startActivity(binding.root.context, intent, null)
                }
            }
        }
    }
}
