package com.example.domain.usecase

import com.example.domain.entities.OrderAndPizzaEntity
import com.example.domain.entities.OrderEntity
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.PizzaRemoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


class OrderUsecase constructor(
    private val orderRepository: OrderRepository,
    private val pizzaRemoteRepository: PizzaRemoteRepository
) {


    fun incrementQuantity(id: Int): Completable {
        return orderRepository.getOrderEntityById(id).flatMapCompletable { oldEntity ->
            orderRepository.updateEntity(OrderEntity(oldEntity.pizzaId, oldEntity.quantity + 1))
        }
    }

    fun decrementQuantity(id: Int): Completable {
        return orderRepository.getOrderEntityById(id).flatMapCompletable { oldEntity ->
            if (oldEntity.quantity == 1) {
                orderRepository.deleteEntity(oldEntity)
            } else {
                orderRepository.updateEntity(OrderEntity(oldEntity.pizzaId, oldEntity.quantity - 1))
            }
        }
    }

    fun deleteOrder(): Completable {
        return orderRepository.deleteOrder()
    }

    fun getOrderWithPizza(): Observable<List<OrderAndPizzaEntity>> {
        return orderRepository.getOrderWithPizza()
    }

    fun getPrice(): Observable<Int> {
        return orderRepository.getOrderWithPizza().map {
            it.sumOf {
                it.pizzaEntity.price.dropLast(1).toInt() * it.orderEntity.quantity
            }
        }
    }

    fun insert(orderEntity: OrderEntity): Completable {
        return orderRepository.addEntity(orderEntity)
    }

    fun postOrder(): Completable {
        return orderRepository.getOrder().flatMapCompletable {
            pizzaRemoteRepository.postOrder(it)
            orderRepository.deleteOrder()
        }
    }

}