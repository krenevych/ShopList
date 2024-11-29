package com.example.shoplist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [ShopEntity::class], version = 1)
public abstract class ShopItemsRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): DaoShopItem

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ShopItemsRoomDatabase? = null

        fun getDatabase(context: Context): ShopItemsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }  // if (INSTANCE != null) return@synchronized INSTANCE!!
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopItemsRoomDatabase::class.java,
                    "shop_items_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}