package com.example.margarettipizza.domain.usecase

import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.repository.local.orderRepository.OrderRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class OrderUsecase @Inject constructor(private val orderRepository: OrderRepository) {

    fun incrementQuantity(id: Int) {

    }

    fun decrementQuantity(id: Int) {

    }

    fun getOrder(): Observable<List<OrderEntity>> {
        return orderRepository.getOrder()
    }

    fun deleteOrder(): Completable {
        return orderRepository.deleteOrder()
    }

}