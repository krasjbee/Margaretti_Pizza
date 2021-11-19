package com.example.margarettipizza.domain.repository

import com.example.margarettipizza.domain.entities.OrderAndPizzaEntity
import com.example.margarettipizza.domain.entities.OrderEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


interface OrderRepository {

    fun getOrder(): Observable<List<OrderEntity>>

    fun deleteEntity(id: Int): Completable

    fun deleteOrder(): Completable

    fun getOrderWithPizza(): Observable<List<OrderAndPizzaEntity>>

    fun getOrderEntityById(id: Int): Single<OrderEntity>

    fun updateEntity(orderDto: OrderEntity): Completable

    fun deleteEntity(orderDto: OrderEntity): Completable

    fun addEntity(orderDto: OrderEntity): Completable

    fun getSingleOrder(): Single<List<OrderAndPizzaEntity>>
}