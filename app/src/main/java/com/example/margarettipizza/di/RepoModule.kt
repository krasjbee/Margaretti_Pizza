package com.example.margarettipizza.di

import com.example.margarettipizza.data.repository.local.orderRepository.OrderRepository
import com.example.margarettipizza.data.repository.local.orderRepository.OrderRepositoryImpl
import com.example.margarettipizza.data.repository.local.pizzaRepository.PizzaLocalRepository
import com.example.margarettipizza.data.repository.local.pizzaRepository.PizzaLocalRepositoryImpl
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

    @Binds
    abstract fun bindOrderRepository(orderRepositoryImpl: OrderRepositoryImpl): OrderRepository
}