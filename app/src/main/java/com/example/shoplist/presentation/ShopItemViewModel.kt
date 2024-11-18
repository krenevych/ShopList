package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecases.AddItemUseCase
import com.example.shoplist.domain.usecases.ChangeItemUseCase
import com.example.shoplist.domain.usecases.GetItemUseCase

class ShopItemViewModel(
    repository: Repository
): ViewModel() {
    private val addItemUseCase = AddItemUseCase(repository)
    private val changeItemUseCase = ChangeItemUseCase(repository)
    private val getItemUseCase = GetItemUseCase(repository)

    fun addItem(name: String, count: Int) {
        addItemUseCase(ShopItem(name=name, count=count))
    }

    fun editItem() {}


}
