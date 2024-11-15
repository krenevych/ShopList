package com.example.shoplist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private val TAG = "XXXX"


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //    private val viewModel by viewModels<MainViewModel>()
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory()
    }

    class MainViewModelFactory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(RepositoryImpl) as T
        }
    }

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