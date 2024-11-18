package com.example.shoplist.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityEditShopItemBinding
import com.example.shoplist.databinding.ShopItemBinding

class ShopItemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEditShopItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}