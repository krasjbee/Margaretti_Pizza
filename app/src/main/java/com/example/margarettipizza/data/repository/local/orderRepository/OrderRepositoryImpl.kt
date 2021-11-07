package com.example.margarettipizza.data.repository.local.orderRepository

import com.example.margarettipizza.data.local.orderDatabase.OrderDao
import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(private val orderDatabase: OrderDao) :
    OrderRepository {

    override fun getOrder(): Observable<List<OrderEntity>> {
        return orderDatabase.getOrder().subscribeOn(Schedulers.io())
    }

    override fun incrementQuantity(): Completable {
        TODO("Not yet implemented")
    }

    override fun decrementQuantity(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteEntity(id: Int): Completable {
        return orderDatabase.deleteEntityById(id).subscribeOn(Schedulers.io())
    }

    override fun deleteOrder(): Completable {
        return orderDatabase.deleteOrder().subscribeOn(Schedulers.io())
    }

    override fun getOrderWithPizza(): Observable<List<OrderWithPizza>> {
        return orderDatabase.getOrderWithPizza().subscribeOn(Schedulers.io())
    }
}