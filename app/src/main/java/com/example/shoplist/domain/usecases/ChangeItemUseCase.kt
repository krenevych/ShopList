package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import javax.inject.Inject

class ChangeItemUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(item: ShopItem) {
        repository.changeItem(item)
    }
}