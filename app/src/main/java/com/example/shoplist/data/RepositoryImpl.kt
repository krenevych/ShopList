package com.example.shoplist.data

import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItem.Companion.UNDEFINED_ID

class RepositoryImpl : Repository {

    private val items: MutableList<ShopItem> = mutableListOf()

    init {
        for (i in 1..3) {
            addItem(ShopItem("Item_$i", i))
        }
    }

    override fun getItems(): List<ShopItem> {
        return items.toList()  // Віддіємо САМЕ копію!
    }

    override fun addItem(item: ShopItem) {
        if (item.id == UNDEFINED_ID) {
            item.id = current_id++
        }
        items.add(item)
    }

    override fun removeItem(item: ShopItem) {
        val itemToRemove = items.find {
            it.id == item.id
        }
        items.remove(itemToRemove)
    }

    override fun toggleEnabled(item: ShopItem) {
        val itemToToggle = items.find {
            it.id == item.id
        }
        itemToToggle ?: return

        val newItem = itemToToggle.copy(isActive = !itemToToggle.isActive)
        removeItem(itemToToggle)
        addItem(newItem)
    }

    override fun changeItem(item: ShopItem) {
        val itemToChange = items.find {
            it.id == item.id
        }
        itemToChange ?: return
        removeItem(itemToChange)
        addItem(item)
    }

    companion object {
        var current_id = 0
    }
}