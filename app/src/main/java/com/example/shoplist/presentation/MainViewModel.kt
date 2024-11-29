package com.example.shoplist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecases.AddItemUseCase
import com.example.shoplist.domain.usecases.ChangeItemUseCase
import com.example.shoplist.domain.usecases.GetItemsUseCase
import com.example.shoplist.domain.usecases.RemoveItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemUseCase: GetItemsUseCase,
    private val addItemUseCase: AddItemUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val changeItemUseCase: ChangeItemUseCase,
) : ViewModel() {

    val itemsLiveData
        get() = getItemUseCase()

    fun addItem(shopItem: ShopItem) {
        viewModelScope.launch {
            addItemUseCase(shopItem)
        }
    }

    fun toggleItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(isActive = !shopItem.isActive)
        viewModelScope.launch {
        changeItemUseCase(newItem)
            }
    }

    fun removeItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeItemUseCase(shopItem)
        }
    }

//    override fun onCleared() {
//        viewModelScope.cancel()
//        super.onCleared()
//    }
}