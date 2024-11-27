package com.meleha.composenavigation.ui.screens.item

import android.util.Log
import androidx.lifecycle.ViewModel
import com.meleha.composenavigation.ItemsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = ItemViewModel.Factory::class)
class ItemViewModel @AssistedInject constructor(
    @Assisted private val args: ItemScreenArgs,
    private val repository: ItemsRepository,
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

    @AssistedFactory
    interface Factory {
        fun create(args: ItemScreenArgs): ItemViewModel
    }
}