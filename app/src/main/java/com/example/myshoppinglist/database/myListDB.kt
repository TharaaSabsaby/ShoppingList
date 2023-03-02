package com.example.myshoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myshoppinglist.database.entity.Item

@Database(
    //entities = [ShoppingList::class, Item::class, Store::class],
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
abstract class myListDB: RoomDatabase() {
    //abstract fun listDao():ListDao
    abstract fun itemDao():ItemDao
    //abstract fun storeDao():StoreDao

    companion object {
        @Volatile
        private var instance: myListDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, myListDB::class.java, "myListDB.db").build()
    }

}
