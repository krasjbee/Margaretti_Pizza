package com.example.margarettipizza.data.local.pizzaDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.margarettipizza.data.local.cacheDatabase.PizzaCacheEntity
import com.example.margarettipizza.data.local.cacheDatabase.PizzaPicDatabase
import com.example.margarettipizza.data.local.typeconverter.ListTypeConverter
import com.example.margarettipizza.data.remote.dto.PizzaDto

@Database(entities = [PizzaDto::class, PizzaCacheEntity::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class PizzaDatabase : RoomDatabase() {

    abstract fun pizzaDao(): PizzaDao

    abstract fun pizzaCacheDao(): PizzaPicDatabase

    companion object {
        //took google sunflower as an example
        @Volatile
        private var database: PizzaDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): PizzaDatabase {
            return database ?: synchronized(this) {
                database ?: Room.databaseBuilder(context, PizzaDatabase::class.java, DATABASE_NAME)
                    .build().also { database = it }
            }
        }

        const val DATABASE_NAME = "pizza_db"

    }
}