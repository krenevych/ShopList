package com.example.shoplist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class GetItemsUseCase(
    private val repository: Repository
) {
    operator fun invoke() : LiveData<List<ShopItem>> {
        return repository.itemsLiveData
    }
}