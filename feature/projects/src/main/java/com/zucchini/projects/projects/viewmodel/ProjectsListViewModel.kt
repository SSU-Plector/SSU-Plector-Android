package com.zucchini.projects.projects.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.ProjectsListModel
import com.zucchini.domain.repository.ProjectsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProjectsListViewModel @Inject constructor(
    private val projectsRepository: ProjectsRepository,
) : ViewModel() {
    private val _projectsList = MutableStateFlow<ProjectsListModel?>(null)
    val projectsList = _projectsList.asStateFlow()

    init {
        getProjectsListData()
    }

    fun getProjectsListData() {
        viewModelScope.launch {
            projectsRepository.getProjectsListData().onSuccess {
                _projectsList.value = it
                Timber.tag("ProjectsListViewModel Success").d(it.toString())
            }.onFailure {
                Timber.tag("ProjectsListViewModel").d(it.toString())
            }
        }
    }
}
