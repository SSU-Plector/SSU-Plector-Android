package com.zucchini.projects.projects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.domain.model.projects.Keyword
import com.zucchini.feature.projects.databinding.ItemSearchKeywordBinding
import com.zucchini.view.ItemDiffCallback

class SearchKeywordAdapter(private val onKeywordClick: (Keyword?) -> Unit) :
    ListAdapter<Keyword, SearchKeywordAdapter.SearchKeywordViewHolder>(
        ItemDiffCallback<Keyword>(
            onItemsTheSame = { old, new -> old.keywordKorean == new.keywordKorean },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    private var selectedPosition: Int? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchKeywordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchKeywordBinding.inflate(inflater, parent, false)
        return SearchKeywordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchKeywordViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class SearchKeywordViewHolder(private val binding: ItemSearchKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: Keyword, position: Int) {
            binding.tvSearchKeyword.text = keyword.keywordKorean
            binding.tvSearchKeyword.isSelected = position == selectedPosition

            binding.tvSearchKeyword.setOnClickListener {
                val previousPosition = selectedPosition
                if (previousPosition != position) {
                    selectedPosition = position
                    notifyItemChanged(previousPosition ?: -1)
                    notifyItemChanged(position)
                    onKeywordClick(keyword)
                } else {
                    selectedPosition = null
                    notifyItemChanged(position)
                    onKeywordClick(null)
                }
            }
        }
    }
}
