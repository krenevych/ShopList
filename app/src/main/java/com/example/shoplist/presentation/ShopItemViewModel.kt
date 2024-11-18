package com.example.shoplist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _itemLiveData = MutableLiveData<ShopItem>()
    val itemLiveData: LiveData<ShopItem>
        get() = _itemLiveData

    fun getItem(id: Int) {
        val shopItem = getItemUseCase(id)
        _itemLiveData.value = shopItem
    }

    fun addItem(name: String, count: Int) {
        addItemUseCase(ShopItem(name=name, count=count))
    }

    fun editItem(name: String, count: Int) {
        itemLiveData.value?.let { shopItem: ShopItem ->
            val copyShopItem = shopItem.copy(name = name, count = count)
            changeItemUseCase(copyShopItem)
        }
    }


}
