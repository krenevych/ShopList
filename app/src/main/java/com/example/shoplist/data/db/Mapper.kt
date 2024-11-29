package com.example.shoplist.data.db

import com.example.shoplist.domain.ShopItem

fun ShopItem.toShopEntity() : ShopEntity {
    return ShopEntity(
        id = this.id,
        name = this.name,
        count = this.count,
        enabled = this.isActive,
    )
}

//fun foo(shopItem: ShopItem) {
//    // some code
//    val shopEntity: ShopEntity = shopItem.toShopEntity()
//    // some code
//}

//Converts List<ShopEntity> to List<ShopItem>
fun entitiesToItems(entities: List<ShopEntity>) = entities.map { it.toShopItem() } // functional approach

//fun entitiesToItems(entities: List<ShopEntity>) : List<ShopItem> {
//    val items = mutableListOf<ShopItem>()
//    for (entity in entities) {
//        val item =  entity.toShopItem()
//        items.add(item)
//    }
//    return items
//}