package com.example.domain.usecase

import com.example.domain.entities.OrderAndPizzaEntity
import com.example.domain.entities.OrderEntity
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.PizzaRemoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable


class OrderUsecase(
    private val orderRepository: OrderRepository,
    private val pizzaRemoteRepository: PizzaRemoteRepository
) {
    private val disposable = CompositeDisposable()

    fun incrementQuantity(id: Int): Completable {
        //little workaround
        return orderRepository.getOrderEntityById(id).flatMapCompletable { orderEntityList ->
            if (orderEntityList.isEmpty()) {
                orderRepository.addEntity(OrderEntity(id, 1))
            } else {
                orderRepository.updateEntity(OrderEntity(id, orderEntityList.first().quantity + 1))
            }
        }
    }

    fun decrementQuantity(id: Int): Completable {
        return orderRepository.getOrderEntityById(id).flatMapCompletable { orderEntityList ->
            if (orderEntityList.isNotEmpty()) {
                val oldOrderEntity = orderEntityList.first()
                if (oldOrderEntity.quantity == 1) {
                    orderRepository.deleteEntity(id)
                } else {
                    orderRepository.updateEntity(OrderEntity(id, oldOrderEntity.quantity - 1))
                }
            } else {
                Completable.error(Throwable("Element does not exist"))
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
        }.andThen { disposable.add(orderRepository.deleteOrder().subscribe()) }
    }

    fun dispose() {
        disposable.dispose()
    }

}