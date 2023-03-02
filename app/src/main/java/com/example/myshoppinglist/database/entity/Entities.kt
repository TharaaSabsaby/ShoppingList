package com.example.myshoppinglist.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "shopping_list")
//data class ShoppingList(
//    @ColumnInfo(name = "list_id")
//    @PrimaryKey
//    val id:Int,
//    val name:String
//)

@Entity(tableName = "items")
data class Item(
    @ColumnInfo(name = "itemName")
    var itemName: String
) {
    // Primary key is a unique key
    // for different database.
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

//@Entity(tableName = "stores")
//data class Store(
//    @ColumnInfo(name = "store_id")
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    val listIdFk: Int
//)