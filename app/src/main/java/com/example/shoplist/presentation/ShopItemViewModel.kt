package com.example.shoplist.presentation

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplist.domain.Repository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecases.AddItemUseCase
import com.example.shoplist.domain.usecases.ChangeItemUseCase
import com.example.shoplist.domain.usecases.GetItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val changeItemUseCase: ChangeItemUseCase,
    private val getItemUseCase: GetItemUseCase,
) : ViewModel() {


    private val _itemLiveData = MutableLiveData<ShopItem>()
    val itemLiveData: LiveData<ShopItem>
        get() = _itemLiveData

    private val _nameErrorLiveData = MutableLiveData<Boolean>()
    val nameErrorLiveData: LiveData<Boolean>
        get() = _nameErrorLiveData

    private val _countErrorLiveData = MutableLiveData<Boolean>()
    val countErrorLiveData: LiveData<Boolean>
        get() = _countErrorLiveData

    private val _finishEditLiveData = MutableLiveData<Unit>()
    val finishEditLiveData: LiveData<Unit>
        get() = _finishEditLiveData

    fun getItem(id: Int) {
        viewModelScope.launch {
            val shopItem = getItemUseCase(id)
            _itemLiveData.value = shopItem
        }
    }


    private var name: String = ""
    private var count: Int = 0
    fun addItem(
//        name: String, count: Int
        nameEditable: Editable?, countEditable: Editable?,
    ) {
        if (parseEditData(nameEditable, countEditable)) {
            viewModelScope.launch {
                addItemUseCase(ShopItem(name = name, count = count))
                finishEditing()
            }
        }
    }

    fun editItem(
//        name: String, count: Int
        nameEditable: Editable?, countEditable: Editable?,
    ) {
        if (parseEditData(nameEditable, countEditable)) {
            itemLiveData.value?.let { shopItem: ShopItem ->
                viewModelScope.launch {
                    val copyShopItem = shopItem.copy(name = name, count = count)
                    changeItemUseCase(copyShopItem)
                    finishEditing()
                }
            }
        }
    }

    private fun parseName(nameEditable: Editable?): Boolean {
        if (nameEditable != null && nameEditable.toString() != "") {
            name = nameEditable.toString()
            return true
        } else {
            _nameErrorLiveData.value = true
        }
        return false
    }

    private fun parseCount(countEditable: Editable?): Boolean {
        if (countEditable != null) {
            try {
                val _count = countEditable.toString().toInt()
                if (_count > 0) {
                    count = _count
                    return true
                } else {
                    _countErrorLiveData.value = true
                }
            } catch (er: NumberFormatException) {
                _countErrorLiveData.value = true
            }
        } else {
            _countErrorLiveData.value = true
        }

        return false
    }

    private fun parseEditData(nameEditable: Editable?, countEditable: Editable?): Boolean {
        var res = true

        if (!parseName(nameEditable)) res = false
        if (!parseCount(countEditable)) res = false

        return res
    }


    private fun finishEditing() {
        _finishEditLiveData.value = Unit
    }

}
