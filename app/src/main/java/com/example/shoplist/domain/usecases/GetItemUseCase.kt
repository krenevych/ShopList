package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class GetItemUseCase(
    private val repository: Repository,
) {
    operator fun invoke(itemId: Int): ShopItem  {
        return repository.getItem(itemId)
    }
}