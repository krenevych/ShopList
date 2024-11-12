package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class AddItemUseCase(
    private val repository: Repository
) {
    fun addItem(item: ShopItem) {
        repository.addItem(item)
    }
}