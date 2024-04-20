package com.zucchini.projects.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.feature.projects.databinding.ItemPageIndicatorBinding

class PageIndicatorAdapter(private val context: Context, private val totalPage: Int) :
    RecyclerView.Adapter<PageIndicatorAdapter.PageIndicatorViewHolder>() {
    private var currentPage = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageIndicatorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageIndicatorBinding.inflate(inflater, parent, false)
        return PageIndicatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageIndicatorViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = totalPage

    fun setCurrentPage(page: Int) {
        currentPage = page
        notifyDataSetChanged()
    }

    inner class PageIndicatorViewHolder(private val binding: ItemPageIndicatorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.pageNumber.text = (position + 1).toString()

            binding.pageNumber.setOnClickListener {
                currentPage = position
                notifyDataSetChanged()
            }
            if (position == currentPage) {
                binding.pageNumber.setTextColor(
                    ContextCompat.getColor(
                        context,
                        com.zucchini.core.designsystem.R.color.olive_black,
                    ),
                )
            } else {
                binding.pageNumber.setTextColor(
                    ContextCompat.getColor(
                        context,
                        com.zucchini.core.designsystem.R.color.gray1,
                    ),
                )
            }
        }
    }
}
