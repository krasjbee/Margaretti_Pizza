package com.example.margarettipizza.data.repository.local.orderRepository

import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


interface OrderRepository {

    fun getOrder(): Observable<List<OrderEntity>>

    fun incrementQuantity(): Completable

    fun decrementQuantity(): Completable

    fun deleteEntity(id: Int): Completable

    fun deleteOrder(): Completable

    fun getOrderWithPizza(): Observable<List<OrderWithPizza>>
}