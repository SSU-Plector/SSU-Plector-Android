package com.zucchini.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.zucchini.context.dialogWidthPercent
import com.zucchini.core.common.databinding.DialogCommonTwoButtonBinding
import com.zucchini.view.setOnSingleClickListener

class TwoButtonCommonDialog : DialogFragment() {
    private lateinit var binding: DialogCommonTwoButtonBinding

    private var confirmButtonClickListener: (() -> Unit)? = null
    private var dismissButtonClickListener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogCommonTwoButtonBinding.inflate(inflater, container, false)
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
        context?.dialogWidthPercent(dialog)
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setConfirmButtonClickListener(confirmButtonClickListener: () -> Unit) {
        this.confirmButtonClickListener = confirmButtonClickListener
    }

    fun setDismissButtonClickListener(dismissButtonClickListener: () -> Unit) {
        this.dismissButtonClickListener = dismissButtonClickListener
    }

    private fun initViews() {
        val title = arguments?.getString(TITLE, "")
        val description = arguments?.getString(DESCRIPTION)
        val confirmButtonText = arguments?.getString(CONFIRM_BUTTON_TEXT, "")
        val dismissButtonText = arguments?.getString(DISMISS_BUTTON_TEXT, "")

        with(binding) {
            tvDialogTitle.text = title
            tvDialogDescription.text = description
            tvConfirmButton.text = confirmButtonText
            tvDismissButton.text = dismissButtonText
        }
    }

    private fun initButtonListener() {
        binding.tvConfirmButton.setOnSingleClickListener {
            confirmButtonClickListener?.invoke()
            dismiss()
        }
        binding.tvDismissButton.setOnSingleClickListener {
            dismissButtonClickListener?.invoke()
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
        const val TAG = "TwoButtonCommonDialog"

        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val CONFIRM_BUTTON_TEXT = "confirmButtonText"
        const val DISMISS_BUTTON_TEXT = "dismissButtonText"

        fun newInstance(
            title: String,
            description: String? = null,
            confirmButtonText: String,
            dismissButtonText: String,
        ): TwoButtonCommonDialog =
            TwoButtonCommonDialog().apply {
                arguments =
                    Bundle().apply {
                        putString(TITLE, title)
                        putString(DESCRIPTION, description)
                        putString(CONFIRM_BUTTON_TEXT, confirmButtonText)
                        putString(DISMISS_BUTTON_TEXT, dismissButtonText)
                    }
            }
    }
}
