package com.example.margarettipizza.di

import android.content.Context
import com.example.margarettipizza.data.local.orderDatabase.OrderDao
import com.example.margarettipizza.data.local.pizzaDatabase.PizzaDao
import com.example.margarettipizza.data.local.pizzaDatabase.PizzaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PizzaDatabase {
        return PizzaDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providePizzaDatabaseDao(pizzaDatabase: PizzaDatabase): PizzaDao {
        return pizzaDatabase.pizzaDao()
    }

    @Provides
    @Singleton
    fun provideOrderDatabase(pizzaDatabase: PizzaDatabase): OrderDao {
        return pizzaDatabase.orderDao()
    }
}