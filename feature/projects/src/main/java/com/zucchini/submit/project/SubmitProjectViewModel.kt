package com.zucchini.submit.project

import androidx.lifecycle.ViewModel
import com.zucchini.domain.model.SubmitProjectInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SubmitProjectViewModel
    @Inject
    constructor() : ViewModel() {
        private val _submitDevInfo = MutableStateFlow<SubmitProjectInfo?>(null)
        val submitDevInfo = _submitDevInfo.asStateFlow()

        fun setSubmitDevInfo(submitProjectInfo: SubmitProjectInfo) {
            _submitDevInfo.value = submitProjectInfo
        }
    }
