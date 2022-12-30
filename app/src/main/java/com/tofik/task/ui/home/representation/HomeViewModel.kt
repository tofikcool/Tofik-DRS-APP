package com.tofik.task.ui.home.representation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {


    fun logOutUser() {
        repository.logOutUser()
    }



}