package com.example.myshoppinglist.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
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
import kotlinx.coroutines.flow.*

class listViewModel(private val repository: shoppingItems) : ViewModel() {

    // In coroutines thread insert item in insert function.
    fun insert(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: Item) = viewModelScope.launch(Dispatchers.IO)  {
        repository.delete(item)
    }

    //    val todoListFlow get() =  repository.allItems()
//    private var todoList = MutableStateFlow
    private val _todoListFlow = MutableStateFlow<List<Item>>(listOf())

    val todoListFlow: StateFlow<List<Item>> get() = _todoListFlow
    private var postExecute: (() -> Unit)? = null

    init {
        loadTodoList()
    }

    fun loadTodoList() {
        viewModelScope.launch {
            repository.allItems().collect {
//                todoList =
                _todoListFlow.value =  it
                postExecute?.invoke()
            }
        }
    }

    fun allShoppingItems() : List<Item>{
        viewModelScope.launch {
            repository.allItems().collect {
//                todoList =
                _todoListFlow.value =  it.toMutableStateList()
                postExecute?.invoke()
            }
        }
        return _todoListFlow.value
    }


//    val items = repository.allItems().asLiveData()
//    fun allShoppingItems() : Flow<Item> = flow {
//        for (i in items.first()){
//            emit(i)
//        }
//    }

}
