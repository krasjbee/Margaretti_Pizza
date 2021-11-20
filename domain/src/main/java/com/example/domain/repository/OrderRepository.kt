package com.example.domain.repository

import com.example.domain.entities.OrderAndPizzaEntity
import com.example.domain.entities.OrderEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


interface OrderRepository {

    fun getOrder(): Single<List<OrderEntity>>

    fun deleteEntity(id: Int): Completable

    fun deleteOrder(): Completable

    fun getOrderWithPizza(): Observable<List<OrderAndPizzaEntity>>

    fun getOrderEntityById(id: Int): Single<List<OrderEntity>>

    fun updateEntity(orderEntity: OrderEntity): Completable

    fun addEntity(orderEntity: OrderEntity): Completable

}