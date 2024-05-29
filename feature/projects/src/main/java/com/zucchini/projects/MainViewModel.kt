package com.zucchini.projects

import androidx.lifecycle.ViewModel
import com.sample.network.datastore.NetworkPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val netPreference: NetworkPreference,
) : ViewModel()
