package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecases.AddItemUseCase
import com.example.shoplist.domain.usecases.ChangeItemUseCase
import com.example.shoplist.domain.usecases.GetItemsUseCase
import com.example.shoplist.domain.usecases.RemoveItemUseCase

class MainViewModel(
    repository: Repository
): ViewModel() {

//    private val repository = RepositoryImpl

    private val getItemUseCase = GetItemsUseCase(repository)
    private val addItemUseCase = AddItemUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val changeItemUseCase = ChangeItemUseCase(repository)

    val itemsLiveData
        get() = getItemUseCase()

    fun addItem(shopItem: ShopItem) = addItemUseCase(shopItem)
    fun toggleItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(isActive = !shopItem.isActive)
        changeItemUseCase(newItem)
    }

    fun removeItem(shopItem: ShopItem) {
        removeItemUseCase(shopItem)
    }


}