package com.example.margarettipizza.data.repository.local.orderRepository

import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


interface OrderRepository {

    fun getOrder(): Observable<List<OrderEntity>>

    fun deleteEntity(id: Int): Completable

    fun deleteOrder(): Completable

    fun getOrderWithPizza(): Observable<List<OrderWithPizza>>

    fun getOrderEntityById(id: Int): Single<OrderEntity>

    fun updateEntity(orderEntity: OrderEntity): Completable

    fun deleteEntity(orderEntity: OrderEntity): Completable

    fun addEntity(orderEntity: OrderEntity): Completable

    fun getSingleOrder(): Single<List<OrderWithPizza>>
}