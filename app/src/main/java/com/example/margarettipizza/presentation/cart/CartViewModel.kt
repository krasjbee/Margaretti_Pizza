package com.example.margarettipizza.presentation.cart

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import com.example.margarettipizza.domain.usecase.OrderUsecase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CartViewModel @Inject constructor(private val orderUsecase: OrderUsecase) : ViewModel() {
    fun getOrderWithPizza(): Observable<List<OrderWithPizza>> {
        return orderUsecase.getOrderWithPizza()
    }

    fun incrementQuantity(id: Int): Completable {
        return orderUsecase.incrementQuantity(id)
    }

    fun decrementQuantity(id: Int): Completable {
        return orderUsecase.decrementQuantity(id)
    }

    fun clearCart(): Completable {
        return orderUsecase.deleteOrder()
    }

    fun getSum(): Observable<Observable<Int>> {
        return orderUsecase.getPrice()
    }

    fun insert(orderEntity: OrderEntity): Completable {
        return orderUsecase.insert(orderEntity)
    }
}