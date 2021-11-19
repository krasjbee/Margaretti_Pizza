package com.example.margarettipizza.di

import com.example.margarettipizza.data.repository.local.orderRepository.OrderRepositoryImpl
import com.example.margarettipizza.data.repository.local.pizzaRepository.PizzaLocalRepositoryImpl
import com.example.margarettipizza.data.repository.remote.PizzaRemoteRepositoryImpl
import com.example.margarettipizza.domain.repository.OrderRepository
import com.example.margarettipizza.domain.repository.PizzaLocalRepository
import com.example.margarettipizza.domain.repository.PizzaRemoteRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {
    @Binds
    abstract fun bindRemoteRepositiory(pizzaRepositoryImpl: PizzaRemoteRepositoryImpl): PizzaRemoteRepository

    @Binds
    abstract fun bindLocalRepository(pizzaLocalRepositoryImpl: PizzaLocalRepositoryImpl): PizzaLocalRepository

    @Binds
    abstract fun bindOrderRepository(orderRepositoryImpl: OrderRepositoryImpl): OrderRepository
}