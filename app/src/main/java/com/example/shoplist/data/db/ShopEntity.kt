package com.example.shoplist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.shoplist.domain.ShopItem.Companion.UNDEFINED_ID

@Entity(tableName = "shop_item_table")
data class ShopEntity (

    @ColumnInfo(name = "id_item")
    var id: Int = UNDEFINED_ID,  // Унікальний ідентифікатор нашого елементу списку покупок

    @ColumnInfo(name = "name_item")
    val name : String,

    @ColumnInfo(name = "count_item")
    val count: Int,

    @ColumnInfo(name = "enabled_item")
    val enabled: Boolean = true,
)
