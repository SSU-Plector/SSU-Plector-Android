package com.zucchini.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.zucchini.context.dialogWidthPercent
import com.zucchini.core.common.databinding.DialogSubmitProjectDevelopersBinding
import com.zucchini.view.setOnSingleClickListener

class SubmitProjectDevelopersDialog : DialogFragment() {
    private lateinit var binding: DialogSubmitProjectDevelopersBinding

    private var confirmButtonClickListener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogSubmitProjectDevelopersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListener()
        initCategoryListner()
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

    private fun initButtonListener() {
        binding.tvConfirmButton.setOnSingleClickListener {
            val developerName = binding.etProjectSubmitDeveloperName.text.toString()
            val developerEmail = binding.etProjectSubmitDeveloperEmail.text.toString()
            val isLeader = binding.cbProjectSubmitDeveloperLeader.isChecked

            val result =
                Bundle().apply {
                    putString("developerName", developerName)
                    putString("developerEmail", developerEmail)
                    putBoolean("isLeader", isLeader)
                }

            parentFragmentManager.setFragmentResult("SubmitProjectDevelopersDialogResult", result)
            dismiss()
        }
    }

    private fun initCategoryListner() {
        binding.tvSubmitProjectDeveloperCategory.setOnSingleClickListener {
            TwoButtonCommonDialog
                .newInstance(
                    title = "",
                    description = "",
                    confirmButtonText = "",
                    dismissButtonText = "",
                ).apply {
                    setConfirmButtonClickListener {
                        dismiss()
                    }
                }.showAllowingStateLoss(childFragmentManager)
        }
    }
}
