package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class GetItemsUseCase(
    private val repository: Repository
) {
    fun getItems() : List<ShopItem> {
        return repository.getItems()
    }
}