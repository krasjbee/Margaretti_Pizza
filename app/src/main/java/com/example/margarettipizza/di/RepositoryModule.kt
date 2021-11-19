package com.example.margarettipizza.di

import com.example.margarettipizza.data.local.orderDatabase.OrderDao
import com.example.margarettipizza.data.local.pizzaDatabase.PizzaDao
import com.example.margarettipizza.data.remote.PizzaRemoteApi
import com.example.margarettipizza.data.repository.local.orderRepository.OrderRepositoryImpl
import com.example.margarettipizza.data.repository.local.pizzaRepository.PizzaLocalRepositoryImpl
import com.example.margarettipizza.data.repository.remote.PizzaRemoteRepositoryImpl
import com.example.margarettipizza.domain.repository.OrderRepository
import com.example.margarettipizza.domain.repository.PizzaLocalRepository
import com.example.margarettipizza.domain.repository.PizzaRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOrderRepoImpl(orderDao: OrderDao): OrderRepository = OrderRepositoryImpl(orderDao)

    @Provides
    @Singleton
    fun providePizzaLocalRepoImpl(pizzaDao: PizzaDao): PizzaLocalRepository =
        PizzaLocalRepositoryImpl(pizzaDao)

    @Provides
    @Singleton
    fun providePizzaRemoteRepoImpl(remoteApi: PizzaRemoteApi): PizzaRemoteRepository =
        PizzaRemoteRepositoryImpl(remoteApi)
}