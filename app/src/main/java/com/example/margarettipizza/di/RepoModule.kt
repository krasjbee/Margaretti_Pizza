package com.example.margarettipizza.di

import com.example.margarettipizza.data.repository.local.PizzaLocalRepository
import com.example.margarettipizza.data.repository.local.PizzaLocalRepositoryImpl
import com.example.margarettipizza.data.repository.remote.PizzaRepository
import com.example.margarettipizza.data.repository.remote.PizzaRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {
    @Binds
    abstract fun bindRemoteRepositiory(pizzaRepositoryImpl: PizzaRepositoryImpl): PizzaRepository

    @Binds
    abstract fun bindLocalRepository(pizzaLocalRepositoryImpl: PizzaLocalRepositoryImpl): PizzaLocalRepository
}