package com.example.margarettipizza.domain.usecase

import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import com.example.margarettipizza.data.repository.local.orderRepository.OrderRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class OrderUsecase @Inject constructor(private val orderRepository: OrderRepository) {


    fun incrementQuantity(id: Int): Completable {
        return orderRepository.getOrderEntityById(id).flatMapCompletable { oldEntity ->
            orderRepository.updateEntity(OrderEntity(oldEntity.id, oldEntity.quantity + 1))
        }
    }

    fun decrementQuantity(id: Int): Completable {
        return orderRepository.getOrderEntityById(id).flatMapCompletable { oldEntity ->
            if (oldEntity.quantity == 1) {
                orderRepository.deleteEntity(oldEntity)
            } else {
                orderRepository.updateEntity(OrderEntity(oldEntity.id, oldEntity.quantity - 1))
            }
        }
    }

    fun getOrder(): Observable<List<OrderEntity>> {
        return orderRepository.getOrder()
    }

    fun deleteOrder(): Completable {
        return orderRepository.deleteOrder()
    }

    fun getOrderWithPizza(): Observable<List<OrderWithPizza>> {
        return orderRepository.getOrderWithPizza()
    }

    fun getPrice(): Observable<Int> {
        return orderRepository.getOrderWithPizza().flatMapIterable<OrderWithPizza> { it }.map {
            it.orderEntity.quantity * it.pizzaDto.price.toInt()
        }.scan { t1, t2 -> t1 + t2 }
    }

    fun insert(entity: OrderEntity): Completable {
        return orderRepository.addEntity(entity)
    }

//    fun getPriceBySingle(): Single<Int> {
//        return orderRepository.getSingleOrder().map<Int>{
//            it.map {
//                it.orderEntity.quantity * it.pizzaDto.price.toInt()
//            }.reduce { acc, i ->
//                acc + i
//            }
//        }
//    }

}