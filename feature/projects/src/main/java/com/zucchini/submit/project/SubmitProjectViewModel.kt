package com.zucchini.submit.project

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.FindDeveloperInfo
import com.zucchini.domain.model.SubmitProjectInfo
import com.zucchini.domain.repository.DevelopersRepository
import com.zucchini.domain.repository.ProjectsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SubmitProjectViewModel
    @Inject
    constructor(
        private val developersRepository: DevelopersRepository,
        private val projectsRepository: ProjectsRepository,
    ) : ViewModel() {
        private val _projectName = MutableStateFlow("")

        private val _imagePath = MutableStateFlow<String>("")

        private val _projectGithub = MutableStateFlow("")

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

        private val _isSuccessSubmitProject = MutableStateFlow(false)
        val isSuccessSubmitProject = _isSuccessSubmitProject.asStateFlow()

        fun updateProjectInfo(
            projectName: String,
            projectGithub: String,
            projectShortIntro: String,
            projectLongIntro: String,
            projectWebLink: String,
            projectAppLink: String,
            projectLink: String,
        ) {
            _projectName.value = projectName
            _projectGithub.value = projectGithub
            _projectShortIntro.value = projectShortIntro
            _projectLongIntro.value = projectLongIntro
            _projectWebLink.value = projectWebLink
            _projectAppLink.value = projectAppLink
            _projectLink.value = projectLink
        }

        fun updateImagePath(imagePath: String) {
            _imagePath.value = imagePath
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

        fun addDeveloperToProject(developerId: Int) {
            _addProjectDeveloperList.value += developerId
        }

        fun removeDeveloperFromProject(developerId: Int) {
            _addProjectDeveloperList.value -= developerId
        }

        fun searchDeveloperList(searchKeyword: String) {
            viewModelScope.launch {
                developersRepository.searchDevelopers(searchKeyword).onSuccess {
                    _searchDeveloperResultList.value = it
                }
            }
        }

        fun submitProject() {
            viewModelScope.launch {
                val submit = SubmitProjectInfo()
                submit.projectName = _projectName.value
                submit.projectGithub = _projectGithub.value
                submit.projectShortIntro = _projectShortIntro.value
                submit.projectLongIntro = _projectLongIntro.value
                submit.projectCategoryList = _projectCategoryList.value
                submit.projectTechStackList = _projectTechStackList.value
                submit.projectLanguageList = _projectLanguageList.value
                submit.projectCooperationList = _projectCooperationList.value
                submit.projectWebLink = _projectWebLink.value
                submit.projectAppLink = _projectAppLink.value
                submit.projectLink = _projectLink.value
                submit.projectDeveloperList = _addProjectDeveloperList.value

                projectsRepository
                    .submitProject(submit, _imagePath.value)
                    .onSuccess {
                        _isSuccessSubmitProject.value = true
                    }.onFailure {
                        _isSuccessSubmitProject.value = false
                        Timber.d("failed to submit project $it")
                        Log.e("SubmitProjectViewModel", "failed to submit project", it)
                    }
            }
        }
    }
