package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItem.Companion.UNDEFINED_ID

object RepositoryImpl : Repository {

    private val items: MutableList<ShopItem> = mutableListOf()

    private val _itemsLiveData = MutableLiveData<List<ShopItem>>()
    override val itemsLiveData: LiveData<List<ShopItem>>
        get() = _itemsLiveData

    init {
        for (i in 1..4) {
            addItem(ShopItem("Item_$i", i))
        }

        addItem(ShopItem("Item_$100", 40, false))
        addItem(ShopItem("Item_$100", 40, false))
    }

    private fun update() {
        _itemsLiveData.value = items.toList()
    }

    override fun addItem(item: ShopItem) {
        if (item.id == UNDEFINED_ID) {
            item.id = current_id++
        }
        items.add(item)

        update()
    }

    override fun removeItem(item: ShopItem) {
        val itemToRemove = items.find {
            it.id == item.id
        }
        items.remove(itemToRemove)

        update()
    }

    override fun changeItem(item: ShopItem) {
        val itemToChange = items.find {
            it.id == item.id
        }
        itemToChange ?: return
//        removeItem(itemToChange)  // To avoid extra call updating of live data
        items.remove(itemToChange)
        addItem(item)
    }

    private var current_id = 0

}