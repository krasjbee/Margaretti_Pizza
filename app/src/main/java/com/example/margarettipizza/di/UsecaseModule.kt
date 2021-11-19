package com.example.margarettipizza.di

import com.example.domain.repository.OrderRepository
import com.example.domain.repository.PizzaLocalRepository
import com.example.domain.repository.PizzaRemoteRepository
import com.example.domain.usecase.OrderUsecase
import com.example.domain.usecase.PizzaUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {

    @Singleton
    @Provides
    fun providePizzaUsecase(
        remoteRepositoryImpl: PizzaRemoteRepository,
        localRepository: PizzaLocalRepository
    ): PizzaUsecase = PizzaUsecase(remoteRepositoryImpl, localRepository)

    @Singleton
    @Provides
    fun provideOrderUsecase(
        orderRepositoryImpl: OrderRepository
    ): OrderUsecase = OrderUsecase(orderRepositoryImpl)
}