package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface Repository {
    // декларуємо, що ми будемо вміти робити з нашими даними
    val itemsLiveData: LiveData<List<ShopItem>>
    suspend fun getItem(id: Int): ShopItem
    suspend fun addItem(item: ShopItem)
    suspend fun removeItem(item: ShopItem)
    suspend fun changeItem(item: ShopItem)

}