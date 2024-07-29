package com.zucchini.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zucchini.context.dialogViewPercent
import com.zucchini.core.common.databinding.DialogCommonSelectCheckboxButtonBinding
import com.zucchini.view.setOnSingleClickListener

class SelectCheckBoxCommonDialog : DialogFragment() {
    private lateinit var binding: DialogCommonSelectCheckboxButtonBinding

    private var confirmButtonClickListener: ((List<String>) -> Unit)? = null
    private var items: List<String> = listOf()
    private val selectedItems = mutableListOf<String>()
    private var maxSelectionCount: Int = Int.MAX_VALUE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogCommonSelectCheckboxButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initButtonListener()
    }

    override fun onResume() {
        super.onResume()
        context?.dialogViewPercent(dialog)
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setConfirmButtonClickListener(confirmButtonClickListener: (List<String>) -> Unit) {
        this.confirmButtonClickListener = confirmButtonClickListener
    }

    private fun initViews() {
        val title = arguments?.getString(TITLE, "")
        val description = arguments?.getString(DESCRIPTION)
        val confirmButtonText = arguments?.getString(CONFIRM_BUTTON_TEXT, "")
        items = arguments?.getStringArrayList(ITEMS) ?: listOf()
        maxSelectionCount = arguments?.getInt(MAX_SELECTION_COUNT, Int.MAX_VALUE) ?: Int.MAX_VALUE

        with(binding) {
            tvDialogTitle.text = title
            tvDialogDescription.text = description
            tvConfirmButton.text = confirmButtonText

            rvDialogContents.layoutManager = LinearLayoutManager(context)
            rvDialogContents.adapter = CheckBoxListAdapter(items, selectedItems, maxSelectionCount)
        }
    }

    private fun initButtonListener() {
        binding.tvConfirmButton.setOnSingleClickListener {
            confirmButtonClickListener?.invoke(selectedItems)
            dismiss()
        }
    }

    fun showAllowingStateLoss(
        fm: FragmentManager,
        tag: String = "",
    ) {
        fm
            .beginTransaction()
            .add(this, tag)
            .commitAllowingStateLoss()
    }

    companion object {
        const val TAG = "SelectCheckBoxCommonDialog"

        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val CONFIRM_BUTTON_TEXT = "confirmButtonText"
        const val ITEMS = "items"
        const val MAX_SELECTION_COUNT = "maxSelectionCount"

        fun newInstance(
            title: String,
            description: String? = null,
            confirmButtonText: String,
            items: ArrayList<String>,
            maxSelectionCount: Int = Int.MAX_VALUE
        ): SelectCheckBoxCommonDialog =
            SelectCheckBoxCommonDialog().apply {
                arguments =
                    Bundle().apply {
                        putString(TITLE, title)
                        putString(DESCRIPTION, description)
                        putString(CONFIRM_BUTTON_TEXT, confirmButtonText)
                        putStringArrayList(ITEMS, items)
                        putInt(MAX_SELECTION_COUNT, maxSelectionCount)
                    }
            }
    }
}
