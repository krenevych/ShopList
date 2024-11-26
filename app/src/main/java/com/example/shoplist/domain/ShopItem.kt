package com.example.shoplist.domain


data class ShopItem(
    val name : String,
    val count: Int,
    val isActive: Boolean = true,
    var id: Int = UNDEFINED_ID  // Унікальний ідентифікатор нашого елементу списку покупок
) {
    companion object {
        const val UNDEFINED_ID = -1
    }

}
