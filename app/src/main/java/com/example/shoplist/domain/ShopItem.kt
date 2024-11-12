package com.example.shoplist.domain


data class ShopItem(
    val name : String,
    val count: Int,
    val isActive: Boolean,
    val id: Int   // Унікальний ідентифікатор нашого елементу списку покупок
)
