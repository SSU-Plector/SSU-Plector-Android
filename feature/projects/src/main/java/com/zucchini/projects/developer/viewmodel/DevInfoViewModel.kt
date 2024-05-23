package com.zucchini.projects.developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.DeveloperDetailInfoInListModel
import com.zucchini.domain.repository.DevelopersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DevInfoViewModel @Inject constructor(
    private val developersRepository: DevelopersRepository,
) : ViewModel() {

    private val _developersList =
        MutableStateFlow<List<DeveloperDetailInfoInListModel>>(emptyList())
    val developersList = _developersList.asStateFlow()

    private val _page = MutableStateFlow(0)
    val page = _page.asStateFlow()

    private val _part = MutableStateFlow("")
    val part = _part.asStateFlow()

    private val _totalPage = MutableStateFlow(0)
    val totalPage = _totalPage.asStateFlow()

    init {
        getDevelopersListData(_page.value, null)
    }

    fun getDevelopersListData(page: Int = _page.value, part: String? = _part.value) {
        viewModelScope.launch {
            developersRepository.getDevelopersListData(page, part).onSuccess {
                _developersList.value = it.developerDetailList
                _totalPage.value = it.totalPage
            }.onFailure {
                Timber.tag("DevInfoViewModel Timber").d(it.toString())
            }
        }
    }

    fun updatePage(page: Int) {
        _page.value = page
        getDevelopersListData(page, _part.value)
    }

    fun updatePart(part: String?) {
        _part.value = part ?: ""
        getDevelopersListData(_page.value, part)
    }
}
