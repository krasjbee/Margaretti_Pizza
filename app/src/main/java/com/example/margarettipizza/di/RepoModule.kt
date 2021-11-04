package com.example.margarettipizza.di

import com.example.margarettipizza.data.repository.PizzaRepository
import com.example.margarettipizza.data.repository.PizzaRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {
    @Binds
    abstract fun provideRemoteRepositiory(pizzaRepositoryImpl: PizzaRepositoryImpl): PizzaRepository
}