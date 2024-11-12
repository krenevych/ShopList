package com.example.shoplist.domain.usecases

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class ToggleEnabledUseCase(
    private val repository: Repository
) {
    fun toggleEnabled(item: ShopItem) {
        repository.toggleEnabled(item)
    }
}