package com.example.data.local.pizzaDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.cacheDatabase.PizzaCacheDto
import com.example.data.local.cacheDatabase.PizzaPicDao
import com.example.data.local.orderDatabase.OrderDao
import com.example.data.local.orderDatabase.OrderDto
import com.example.data.local.typeconverter.ListTypeConverter
import com.example.data.remote.dto.PizzaDto

@Database(entities = [PizzaDto::class, PizzaCacheDto::class, OrderDto::class], version = 3)
@TypeConverters(ListTypeConverter::class)
abstract class PizzaDatabase : RoomDatabase() {

    abstract fun pizzaDao(): PizzaDao

    abstract fun pizzaCacheDao(): PizzaPicDao

    abstract fun orderDao(): OrderDao

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