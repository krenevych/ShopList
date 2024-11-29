package com.example.shoplist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface DaoShopItem {

    @Query("SELECT * FROM shop_item_table ORDER BY id_item ASC")
    fun itemsLiveData(): LiveData<List<ShopEntity>>  // when function returns LiveData it has suspend-functionality under the hood.

    @Query("SELECT * FROM shop_item_table WHERE id_item LIKE :id LIMIT 1")
    suspend fun getItem(id: Int): ShopEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ShopEntity)

    @Delete
    suspend fun removeItem(item: ShopEntity)

    @Update
    suspend fun changeItem(item: ShopEntity)

}