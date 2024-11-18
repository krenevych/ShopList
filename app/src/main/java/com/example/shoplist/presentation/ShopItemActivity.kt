package com.example.shoplist.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityEditShopItemBinding
import com.example.shoplist.databinding.ShopItemBinding
import com.example.shoplist.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private val TAG: String  = "XXXX"
    private val binding by lazy {
        ActivityEditShopItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        
        parseItemIntent(intent)
        Log.d(TAG, "parse intent: mode=$mode, id=$itemId")
        
    }

    
    private var mode: String = MODE_UNDEFINED
    private var itemId = ShopItem.UNDEFINED_ID
    private fun parseItemIntent(intent: Intent) {
        if (intent.hasExtra(EXTRA_MODE)) { 
            mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_UNDEFINED
            when(mode) {
                MODE_EDIT -> {
                    if (intent.hasExtra(EXTRA_ITEM_ID)) {
                        itemId = intent.getIntExtra(EXTRA_ITEM_ID,  ShopItem.UNDEFINED_ID)
                        if (itemId == ShopItem.UNDEFINED_ID) {
                            throw IllegalArgumentException("Mode Edit, item Id isn't defined")
                        }
                    } else {
                        throw IllegalArgumentException("Mode Edit, item Id isn't defined")
                    }
                }
                MODE_ADD -> {
                    
                }
                MODE_UNDEFINED -> throw IllegalArgumentException("Mode isn't defined")
            }
        } else {
            throw IllegalArgumentException("Mode isn't defined")
        }
    }


}