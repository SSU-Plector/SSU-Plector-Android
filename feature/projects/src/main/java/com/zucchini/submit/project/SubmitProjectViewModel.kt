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

        private val _projectName = MutableStateFlow("")
        val projectName = _projectName.asStateFlow()

        private val _projectGithub = MutableStateFlow("")
        val projectGithub = _projectGithub.asStateFlow()

        private val _imagePath = MutableStateFlow("")
        val imagePath = _imagePath.asStateFlow()

        private val _projectShortIntro = MutableStateFlow("")
        val projectShortIntro = _projectShortIntro.asStateFlow()

        private val _projectLongIntro = MutableStateFlow("")
        val projectLongIntro = _projectLongIntro.asStateFlow()

        private val _projectCategoryList = MutableStateFlow(emptyList<String>())
        val projectCategoryList = _projectCategoryList.asStateFlow()

        private val _projectTechStackList = MutableStateFlow(emptyList<String>())
        val projectTechStackList = _projectTechStackList.asStateFlow()

        private val _projectLanguageList = MutableStateFlow(emptyList<String>())
        val projectLanguageList = _projectLanguageList.asStateFlow()

        private val _projectCooperationList = MutableStateFlow(emptyList<String>())
        val projectCooperationList = _projectCooperationList.asStateFlow()

        private val _projectWebLink = MutableStateFlow("")
        val projectWebLink = _projectWebLink.asStateFlow()

        private val _projectAppLink = MutableStateFlow("")
        val projectAppLink = _projectAppLink.asStateFlow()

        private val _projectLink = MutableStateFlow("")
        val projectLink = _projectLink.asStateFlow()

        private val _projectDeveloperList = MutableStateFlow(emptyList<Int>())
        val projectDeveloperList = _projectDeveloperList.asStateFlow()

        fun setSubmitDevInfo(submitProjectInfo: SubmitProjectInfo) {
            _submitDevInfo.value = submitProjectInfo
        }

        fun setProjectName(projectName: String) {
            _projectName.value = projectName
        }

        fun updateProjectInfo(
            projectName: String,
            projectGithub: String,
            imagePath: String,
            projectShortIntro: String,
            projectLongIntro: String,
            projectWebLink: String,
            projectAppLink: String,
            projectLink: String,
        ) {
            _projectName.value = projectName
            _projectGithub.value = projectGithub
            _imagePath.value = imagePath
            _projectShortIntro.value = projectShortIntro
            _projectLongIntro.value = projectLongIntro
            _projectWebLink.value = projectWebLink
            _projectAppLink.value = projectAppLink
            _projectLink.value = projectLink
        }

        fun updateProjectCategory(projectCategoryList: List<String> = emptyList()) {
            _projectCategoryList.value = projectCategoryList
        }

        fun updateProjectTechStack(projectTechStackList: List<String> = emptyList()) {
            _projectTechStackList.value = projectTechStackList
        }

        fun updateProjectLanguage(projectLanguageList: List<String> = emptyList()) {
            _projectLanguageList.value = projectLanguageList
        }

        fun updateProjectCooperation(projectCooperationList: List<String> = emptyList()) {
            _projectCooperationList.value = projectCooperationList
        }

        fun updateDeveloperList(projectDeveloperList: List<Int>) {
            _projectDeveloperList.value = projectDeveloperList
        }
    }
