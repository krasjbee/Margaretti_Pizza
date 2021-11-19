package com.example.margarettipizza.di

import com.example.data.local.orderDatabase.OrderDao
import com.example.data.local.pizzaDatabase.PizzaDao
import com.example.data.remote.PizzaRemoteApi
import com.example.data.repository.local.orderRepository.OrderRepositoryImpl
import com.example.data.repository.local.pizzaRepository.PizzaLocalRepositoryImpl
import com.example.data.repository.remote.PizzaRemoteRepositoryImpl
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.PizzaLocalRepository
import com.example.domain.repository.PizzaRemoteRepository
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