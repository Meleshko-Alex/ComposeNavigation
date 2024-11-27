package com.meleha.composenavigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ItemsRepository,
) : ViewModel() {

    fun clear() {
        repository.clear()
    }
}