package com.example.myshoppinglist.database

import com.example.myshoppinglist.database.entity.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class shoppingItems(private val db: myListDB) {
    suspend fun insert(item: Item) = db.itemDao().insert(item)
    suspend fun delete(item: Item) = db.itemDao().delete(item)
    fun allItems() = db.itemDao().getAllItems()
    /*fun allItems() : Flow<List<Item>> {
        return db.itemDao().getAllItems()
            .map { item ->
                item
            }
    }*/
}