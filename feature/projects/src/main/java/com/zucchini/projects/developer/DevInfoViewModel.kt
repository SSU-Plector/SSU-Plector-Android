package com.zucchini.projects.developer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zucchini.domain.model.DevelopersListModel
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
    private val _developersList = MutableStateFlow<DevelopersListModel?>(null)
    val developersList = _developersList.asStateFlow()

    init {
        getDevelopersListData()
    }

    fun getDevelopersListData() {
        viewModelScope.launch {
            developersRepository.getDevelopersListData().onSuccess {
                _developersList.value = it
                Timber.tag("DevInfoViewModel Success").d(it.toString())
            }.onFailure {
                Timber.tag("DevInfoViewModel Timber").d(it.toString())
            }
        }
    }

//    fun getDevelopersDetailData(developerId: Int) {
//        viewModelScope.launch {
//            developersRepository.getDevelopersDetailData(developerId).onSuccess {
//                _developersDetail.value = it
//                Timber.tag("DevInfoViewModel").d(it.toString())
//            }.onFailure {
//                Timber.tag("DevInfoViewModel").d(it.toString())
//            }
//        }
//    }
}
