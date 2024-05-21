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

    private val _totalPage = MutableStateFlow(0)
    val totalPage = _totalPage.asStateFlow()

    init {
        getProjectsListData()
    }

    fun getProjectsListData(
        searchString: String = _searchString.value,
        category: String = _category.value,
        sortOption: SortOption = _sortOption.value,
        page: Int = _page.value,
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
                    _totalPage.value = it.totalPage
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

    fun updatePage(page: Int) {
        _page.value = page
        getProjectsListData(page = page)
    }
}
