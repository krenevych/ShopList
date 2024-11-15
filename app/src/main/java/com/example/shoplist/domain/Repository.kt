package com.example.shoplist.domain

import androidx.lifecycle.LiveData

interface Repository {
    // декларуємо, що ми будемо вміти робити з нашими даними
    val itemsLiveData : LiveData<List<ShopItem>>
    fun addItem(item: ShopItem)
    fun removeItem(item: ShopItem)
    fun changeItem(item: ShopItem)
}