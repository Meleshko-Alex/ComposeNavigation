package com.meleha.composenavigation.ui.screens.item

import android.util.Log
import androidx.lifecycle.ViewModel
import com.meleha.composenavigation.ItemsRepository

class ItemViewModel(
    private val args: ItemScreenArgs,
    private val repository: ItemsRepository = ItemsRepository.get(),
) : ViewModel() {

    init {
        Log.i("AAA", "ItemViewModel-${hashCode()} created")
    }

    fun getInitialValue(): String {
        return when(args) {
            is ItemScreenArgs.Add -> ""
            is ItemScreenArgs.Edit -> repository.getItems().value[args.index]
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("AAA", "ItemViewModel-${hashCode()} destroyed")
    }
}