package com.example.data.repository.local.orderRepository

import com.example.data.local.orderDatabase.OrderDao
import com.example.data.local.orderDatabase.convertToOrderDto
import com.example.data.local.orderDatabase.convertToOrderEntity
import com.example.data.local.orderDatabase.relations.convertToOrderAndPizzaEntity
import com.example.domain.entities.OrderAndPizzaEntity
import com.example.domain.entities.OrderEntity
import com.example.domain.repository.OrderRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class OrderRepositoryImpl(private val orderDatabase: OrderDao) :
    OrderRepository {

    override fun getOrder(): Single<List<OrderEntity>> {
        return orderDatabase.getOrder().map {
            it.map {
                it.convertToOrderEntity()
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun deleteEntity(id: Int): Completable {
        return orderDatabase.deleteEntityById(id).subscribeOn(Schedulers.io())
    }

    override fun deleteOrder(): Completable {
        return orderDatabase.deleteOrder().subscribeOn(Schedulers.io())
    }

    override fun getOrderWithPizza(): Observable<List<OrderAndPizzaEntity>> {
        return orderDatabase.getOrderWithPizza().subscribeOn(Schedulers.io()).map {
            it.map {
                it.convertToOrderAndPizzaEntity()
            }
        }
    }

    override fun getOrderEntityById(id: Int): Single<List<OrderEntity>> {
        return orderDatabase.getEntityById(id).subscribeOn(Schedulers.io()).map {
            it.map {
                it.convertToOrderEntity()
            }
        }
    }

    override fun updateEntity(orderEntity: OrderEntity): Completable {
        return orderDatabase.updateEntity(orderEntity.convertToOrderDto())
            .subscribeOn(Schedulers.io())
    }

    override fun addEntity(orderEntity: OrderEntity): Completable {
        return orderDatabase.insertEntity(orderEntity.convertToOrderDto())
            .subscribeOn(Schedulers.io())
    }

}