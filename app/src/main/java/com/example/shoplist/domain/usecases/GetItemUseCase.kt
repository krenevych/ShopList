package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(itemId: Int): ShopItem  {
        return repository.getItem(itemId)
    }
}