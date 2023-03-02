package com.example.myshoppinglist.ui

import androidx.lifecycle.ViewModel
import com.example.myshoppinglist.database.entity.Item
import com.example.myshoppinglist.database.shoppingItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class listViewModel(private val repository: shoppingItems) : ViewModel() {

    // In coroutines thread insert item in insert function.
    fun insert(item: Item) = viewModelScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: Item) = viewModelScope.launch {
        repository.delete(item)
    }

    fun allShoppingItems() = repository.allItems().asLiveData()
//    val items = repository.allItems().asLiveData()
//    fun allShoppingItems() : Flow<Item> = flow {
//        for (i in items.first()){
//            emit(i)
//        }
//    }

}
