package com.zucchini.submit.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.FindDeveloperInfo
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.feature.projects.databinding.ItemFindDevBinding
import com.zucchini.view.ItemDiffCallback

class FindDevAdapter(
    private val submitProjectInfo: SubmitProjectInfo
) : ListAdapter<FindDeveloperInfo, FindDevAdapter.FindDevViewHolder>(
    ItemDiffCallback<FindDeveloperInfo>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FindDevViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFindDevBinding.inflate(inflater, parent, false)
        return FindDevViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FindDevViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    inner class FindDevViewHolder(
        private val binding: ItemFindDevBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(findDevInfo: FindDeveloperInfo) {
            binding.run {
                tvDeveloperName.text = findDevInfo.developerName
                tvDeveloperEmail.text = findDevInfo.developerEmail

                cbFindDev.isChecked = submitProjectInfo.projectDeveloperList.contains(findDevInfo.developerId)

                root.setOnClickListener {
                    cbFindDev.isChecked = !cbFindDev.isChecked
                    if (cbFindDev.isChecked) {
                        submitProjectInfo.projectDeveloperList += findDevInfo.developerId
                    } else {
                        submitProjectInfo.projectDeveloperList -= findDevInfo.developerId
                    }
                }
            }
        }
    }
}
