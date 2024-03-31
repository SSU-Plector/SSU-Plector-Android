package com.zucchini.projects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.feature.projects.databinding.ItemSearchKeywordBinding
import com.zucchini.view.ItemDiffCallback
import com.zucchini.domain.model.Keyword

class SearchKeywordAdapter : ListAdapter<Keyword, SearchKeywordAdapter.SearchKeywordViewHolder>(
    ItemDiffCallback<Keyword>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new },
    ),
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchKeywordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchKeywordBinding.inflate(inflater, parent, false)
        return SearchKeywordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchKeywordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchKeywordViewHolder(private val binding: ItemSearchKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: Keyword) {
            binding.tvSearchKeyword.text = keyword.keyword
        }
    }
}
