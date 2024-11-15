package com.example.shoplist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private val TAG = "XXXX"


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.itemsLiveData.observe(this) {
            // Calls each time, when data is changed
            Log.d(TAG, "getItemUseCase: $it")
        }


        binding.addBtn.setOnClickListener {
            viewModel.addItem(ShopItem("Bread", 4))
        }

    }
}