package com.example.shoplist.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shoplist.data.db.ShopEntity
import com.example.shoplist.data.db.ShopItemsRoomDatabase
import com.example.shoplist.data.db.entitiesToItems
import com.example.shoplist.data.db.toShopEntity
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryDataBase @Inject constructor(@ApplicationContext context: Context) : Repository {

    private val dao = ShopItemsRoomDatabase.getDatabase(context).wordDao()

    override val itemsLiveData: LiveData<List<ShopItem>>
        get() {
            val entityLiveData: LiveData<List<ShopEntity>> = dao.itemsLiveData()

            val mediatorLiveData = MediatorLiveData<List<ShopItem>>()

            mediatorLiveData.addSource(entityLiveData) { entities ->
                mediatorLiveData.value = entitiesToItems(entities)  // converter from List<ShopEntity> to List<ShopItem>
            }

            return mediatorLiveData
        }

    override fun getItem(id: Int): ShopItem {
        return dao.getItem(id).toShopItem()
    }

    override fun addItem(item: ShopItem) {
        dao.addItem(item.toShopEntity())
    }

    override fun removeItem(item: ShopItem) {
        dao.removeItem(item.toShopEntity())
    }

    override fun changeItem(item: ShopItem) {
        dao.changeItem(item.toShopEntity())
    }
}