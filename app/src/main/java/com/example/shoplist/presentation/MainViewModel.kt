package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecases.AddItemUseCase
import com.example.shoplist.domain.usecases.ChangeItemUseCase
import com.example.shoplist.domain.usecases.GetItemsUseCase
import com.example.shoplist.domain.usecases.RemoveItemUseCase

class MainViewModel: ViewModel() {

    private val repository = RepositoryImpl


    private val getItemUseCase = GetItemsUseCase(repository)
    private val addItemUseCase = AddItemUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val changeItemUseCase = ChangeItemUseCase(repository)

    fun getItems() = getItemUseCase()
    fun addItem(shopItem: ShopItem) = addItemUseCase(shopItem)

//    val bread = ShopItem("Bread", 4)
//    Log.d(TAG, "getItemUseCase: ${getItemUseCase()}")
//    addItemUseCase(bread)
//    addItemUseCase(ShopItem("Bear", 2))
//    Log.d(TAG, "getItemUseCase: ${getItemUseCase()}")
//    removeItemUseCase(bread)
//    Log.d(TAG, "getItemUseCase: ${getItemUseCase()}")


}