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