package com.example.myshoppinglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myshoppinglist.database.shoppingItems

class listVMFactory (private val repository: shoppingItems): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return listViewModel(repository) as T
    }
}
