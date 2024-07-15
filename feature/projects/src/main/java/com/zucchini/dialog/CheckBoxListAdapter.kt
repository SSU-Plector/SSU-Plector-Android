package com.zucchini.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.core.common.databinding.ItemDialogCheckboxBinding
import com.zucchini.domain.model.Keyword

class CheckBoxListAdapter(
    private val items: List<String>,
    private val selectedItems: MutableList<String>
) : RecyclerView.Adapter<CheckBoxListAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemDialogCheckboxBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemDialogCheckboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.binding.cbDialogContent.text = item

        holder.binding.cbDialogContent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedItems.add(item)
            } else {
                selectedItems.remove(item)
            }
        }
    }

    override fun getItemCount() = items.size
}
