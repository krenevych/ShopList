package com.example.shoplist.data

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem

class RepositoryImpl : Repository {

    private val items: MutableList<ShopItem> = mutableListOf()

    init {
        for (i in 1..10) {
            items.add(ShopItem("Item_$i", i))
        }
    }

    override fun getItems(): List<ShopItem> {
        return items.toList()  // Віддіємо САМЕ копію!
    }

    override fun addItem(item: ShopItem) {
        items.add(item)
    }

    override fun removeItem(item: ShopItem) {
        val itemToRemove = items[item.id]
        items.remove(itemToRemove)
    }

    override fun toggleEnabled(item: ShopItem) {
        val itemToToggle = items[item.id]
        val newItem = itemToToggle.copy(isActive = !itemToToggle.isActive)
        removeItem(itemToToggle)
        addItem(newItem)
    }

    override fun changeItem(item: ShopItem) {
        val itemToChange = items[item.id]
        removeItem(itemToChange)
        addItem(item)
    }
}