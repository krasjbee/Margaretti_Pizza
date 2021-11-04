package com.example.margarettipizza.di

import android.content.Context
import com.example.margarettipizza.data.local.PizzaDatabase
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
}