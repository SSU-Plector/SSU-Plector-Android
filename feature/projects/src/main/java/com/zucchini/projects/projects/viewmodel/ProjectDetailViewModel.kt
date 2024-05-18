package com.zucchini.projects.projects.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.ProjectsDetailModel
import com.zucchini.domain.repository.ProjectsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProjectDetailViewModel @Inject constructor(
    private val projectsRepository: ProjectsRepository,
) : ViewModel() {
    private val _projectsDetail = MutableStateFlow<ProjectsDetailModel?>(null)
    val projectsDetail = _projectsDetail.asStateFlow()

    private val _devListInProjectsDetail =
        MutableStateFlow<List<ProjectsDetailModel.DeveloperListInProjectDetail?>>(emptyList())
    val devListInProjectsDetail = _devListInProjectsDetail.asStateFlow()

    fun loadProjectsDetail(projectId: Int) {
        viewModelScope.launch {
            projectsRepository.getProjectsDetailData(projectId).onSuccess {
                _projectsDetail.value = it
                _devListInProjectsDetail.value = it.developerList
                Timber.tag("ProjectDetailViewModel").d(it.toString())
            }.onFailure {
                Timber.tag("ProjectDetailViewModel Fail").d(it.toString())
            }
        }
    }
}
