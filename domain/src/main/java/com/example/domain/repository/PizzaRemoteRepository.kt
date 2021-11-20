package com.example.domain.repository

import com.example.domain.entities.OrderEntity
import com.example.domain.entities.PizzaEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PizzaRemoteRepository {

    fun getAll(): Single<List<PizzaEntity>>

    fun getPizzaById(id: Int): Single<PizzaEntity>

    fun postOrder(order: List<OrderEntity>): Completable

}