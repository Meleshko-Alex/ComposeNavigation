package com.meleha.composenavigation.ui.screens.items

import androidx.lifecycle.ViewModel
import com.meleha.composenavigation.ItemsRepository
import com.meleha.composenavigation.ui.screens.item.ItemScreenArgs
import com.meleha.composenavigation.ui.screens.item.ItemScreenResponse

class ItemsViewModel(
    private val repository: ItemsRepository = ItemsRepository.get()
) : ViewModel() {

    val itemsFlow = repository.getItems()

    fun processResponse(response: ItemScreenResponse) {
        when (response.args) {
            is ItemScreenArgs.Add -> repository.addItem(response.newValue)
            is ItemScreenArgs.Edit -> repository.updateItem(response.args.index, response.newValue)
        }
    }
}