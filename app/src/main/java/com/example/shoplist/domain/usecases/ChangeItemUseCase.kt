package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class ChangeItemUseCase(
    private val repository: Repository
) {
    operator fun invoke(item: ShopItem) {
        repository.changeItem(item)
    }
}