package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItem.Companion.UNDEFINED_ID
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

//    private val items: MutableList<ShopItem> = mutableListOf()

    private val items = sortedSetOf<ShopItem>({item1: ShopItem, item2:ShopItem ->
        item1.id - item2.id
    })

    private val _itemsLiveData = MutableLiveData<List<ShopItem>>()
    override val itemsLiveData: LiveData<List<ShopItem>>
        get() = _itemsLiveData

    override suspend fun getItem(id: Int): ShopItem {
        return items.find {
            it.id == id
        } ?: throw IllegalStateException("Item $id isn't found")
    }

    init {
        items.add(ShopItem(name = "Item_100", count = 40, id = current_id++, isActive = false))
        for (i in 1..4) {
            items.add(ShopItem(name = "Item_$i", count = i, id = current_id++))
        }
        items.add(ShopItem(name = "Item_100", count = 40, id = current_id++, isActive = false))

        update()
    }

    private fun update() {
        _itemsLiveData.value = items.toList()
    }

    override suspend fun addItem(item: ShopItem) {
        if (item.id == UNDEFINED_ID) {
            item.id = current_id++
        }
        items.add(item)

        update()
    }

    override suspend fun removeItem(item: ShopItem) {
        val itemToRemove = items.find {
            it.id == item.id
        }
        items.remove(itemToRemove)

        update()
    }

    override suspend fun changeItem(item: ShopItem) {
        val itemToChange = items.find {
            it.id == item.id
        }
        itemToChange ?: return
//        removeItem(itemToChange)  // To avoid extra call updating of live data
        items.remove(itemToChange)
        addItem(item)
    }

    companion object {
        private var current_id = 1
    }

}