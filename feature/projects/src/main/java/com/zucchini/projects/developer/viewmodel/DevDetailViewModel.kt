package com.zucchini.projects.developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.DevelopersDetailModel
import com.zucchini.domain.repository.DevelopersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DevDetailViewModel @Inject constructor(
    private val developersRepository: DevelopersRepository,
) : ViewModel() {

    private val _developersDetail = MutableStateFlow<DevelopersDetailModel?>(null)
    val developersDetail = _developersDetail.asStateFlow()

    init {
        loadDevelopersDetail(1)
    }
    fun loadDevelopersDetail(developerId: Int) {
        viewModelScope.launch {
            developersRepository.getDevelopersDetailData(developerId).onSuccess {
                _developersDetail.value = it
                Timber.tag("DevDetailViewModel").d(it.toString())
            }.onFailure {
                Timber.tag("DevDetailViewModel Fail").d(it.toString())
            }
        }
    }
}