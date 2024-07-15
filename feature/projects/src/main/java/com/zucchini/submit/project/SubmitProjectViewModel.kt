package com.zucchini.submit.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.FindDeveloperInfo
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.domain.repository.DevelopersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubmitProjectViewModel
    @Inject
    constructor(
        private val developersRepository: DevelopersRepository,
    ) : ViewModel() {
        private val _submitDevInfo = MutableStateFlow<SubmitProjectInfo?>(null)
        val submitDevInfo = _submitDevInfo.asStateFlow()

        private val _projectName = MutableStateFlow("")

        private val _projectGithub = MutableStateFlow("")

        private val _imagePath = MutableStateFlow("")

        private val _projectShortIntro = MutableStateFlow("")

        private val _projectLongIntro = MutableStateFlow("")

        private val _projectCategoryList = MutableStateFlow(emptyList<String>())

        private val _projectTechStackList = MutableStateFlow(emptyList<String>())

        private val _projectLanguageList = MutableStateFlow(emptyList<String>())

        private val _projectCooperationList = MutableStateFlow(emptyList<String>())

        private val _projectWebLink = MutableStateFlow("")

        private val _projectAppLink = MutableStateFlow("")

        private val _projectLink = MutableStateFlow("")

        private val _addProjectDeveloperList = MutableStateFlow(emptyList<Int>())

        private val _searchDeveloperResultList = MutableStateFlow(emptyList<FindDeveloperInfo>())
        val searchDeveloperResultList = _searchDeveloperResultList.asStateFlow()

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
            _addProjectDeveloperList.value = projectDeveloperList
        }

        fun searchDeveloperList(searchKeyword: String) {
            viewModelScope.launch {
                developersRepository.searchDevelopers(searchKeyword).onSuccess {
                    _searchDeveloperResultList.value = it
                }
            }
        }
    }
