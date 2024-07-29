package com.zucchini.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zucchini.core.common.databinding.ItemDialogCheckboxBinding

class CheckBoxListAdapter(
    private val items: List<String>,
    private val selectedItems: MutableList<String>,
    private val maxSelectionCount: Int
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

        // Initialize checkbox state
        holder.binding.cbDialogContent.isChecked = selectedItems.contains(item)

        holder.binding.cbDialogContent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Check if the maximum selection count is not exceeded
                if (selectedItems.size < maxSelectionCount) {
                    selectedItems.add(item)
                } else {
                    holder.binding.cbDialogContent.isChecked = false
                }
            } else {
                selectedItems.remove(item)
            }
        }
    }

    override fun getItemCount() = items.size
}
