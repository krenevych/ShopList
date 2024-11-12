package com.example.shoplist.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecases.AddItemUseCase
import com.example.shoplist.domain.usecases.ToggleEnabledUseCase
import com.example.shoplist.domain.usecases.GetItemsUseCase
import com.example.shoplist.domain.usecases.RemoveItemUseCase

class MainActivity : AppCompatActivity() {
    private val TAG = "XXXX"

    private val repository = RepositoryImpl()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val getItemUseCase = GetItemsUseCase(repository)
        val addItemUseCase = AddItemUseCase(repository)
        val removeItemUseCase = RemoveItemUseCase(repository)
        val toggleEnabledUseCase = ToggleEnabledUseCase(repository)

        val bread = ShopItem("Bread", 4)

        Log.d(TAG, "getItemUseCase: ${getItemUseCase.getItems()}")
        addItemUseCase.addItem(bread)
        addItemUseCase.addItem(ShopItem("Bear", 2))
        Log.d(TAG, "getItemUseCase: ${getItemUseCase.getItems()}")
        toggleEnabledUseCase.toggleEnabled(bread)
        Log.d(TAG, "getItemUseCase: ${getItemUseCase.getItems()}")
        removeItemUseCase.removeItem(bread)
        Log.d(TAG, "getItemUseCase: ${getItemUseCase.getItems()}")

    }
}