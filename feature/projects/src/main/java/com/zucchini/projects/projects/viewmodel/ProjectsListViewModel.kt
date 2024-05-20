package com.zucchini.projects.projects.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.ProjectListInfoInList
import com.zucchini.domain.model.SortOption
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
    private val _projectsList = MutableStateFlow<List<ProjectListInfoInList?>>(emptyList())
    val projectsList = _projectsList.asStateFlow()

    private val _searchString = MutableStateFlow("")
    val searchString = _searchString.asStateFlow()

    private val _category = MutableStateFlow("SERVICE")
    val category = _category.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.RECENT)
    val sortOption = _sortOption.asStateFlow()

    private val _page = MutableStateFlow(0)
    val page = _page.asStateFlow()

    init {
        getProjectsListData(
            _searchString.value,
            _category.value,
            _sortOption.value,
            _page.value,
        )
    }

    private fun getProjectsListData(
        searchString: String,
        category: String,
        sortOption: SortOption,
        page: Int,
    ) {
        viewModelScope.launch {
            projectsRepository.getProjectsListData(
                searchString,
                category,
                sortOption.displayName,
                page,
            )
                .onSuccess {
                    _projectsList.value = it.projectListInfoInList
                    Timber.tag("ProjectsListViewModel Success").d(_projectsList.value.toString())
                }.onFailure {
                    Timber.tag("ProjectsListViewModel").d(it.toString())
                }
        }
    }

    fun updateSortOption(option: SortOption) {
        _sortOption.value = option
        getProjectsListData(
            _searchString.value,
            _category.value,
            _sortOption.value,
            _page.value,
        )
    }

    fun updateSearchString(newSearchString: String) {
        _searchString.value = newSearchString

        getProjectsListData(
            _searchString.value,
            _category.value,
            _sortOption.value,
            _page.value,
        )
    }
}
