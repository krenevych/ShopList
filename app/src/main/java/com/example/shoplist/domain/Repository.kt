package com.example.shoplist.domain

interface Repository {
    // декларуємо, що ми будемо вміти робити з нашими даними
    fun getItems() : List<ShopItem>
    fun addItem(item: ShopItem)
    fun removeItem(item: ShopItem)
    fun toggleEnabled(item: ShopItem)
    fun changeItem(item: ShopItem)
}