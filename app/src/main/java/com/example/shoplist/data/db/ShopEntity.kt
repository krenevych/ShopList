package com.example.shoplist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoplist.domain.ShopItem

@Entity(tableName = "shop_item_table")
data class ShopEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_item")
    val id: Int,

    @ColumnInfo(name = "name_item")
    val name : String,

    @ColumnInfo(name = "count_item")
    val count: Int,

    @ColumnInfo(name = "enabled_item")
    val enabled: Boolean = true,
) {

    fun toShopItem() : ShopItem {
        return ShopItem(
            name = this.name,
            count = this.count,
            isActive = this.enabled,
            id = this.id
        )
    }

}



//fun foo(entity: ShopEntity){
//    /// some code
//    val shopItem = entity.toShopItem()
//    /// some code
//
//}
