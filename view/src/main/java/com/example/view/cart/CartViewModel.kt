package com.example.view.cart

import androidx.lifecycle.ViewModel
import com.example.domain.entities.OrderAndPizzaEntity
import com.example.domain.usecase.OrderUsecase
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CartViewModel @Inject constructor(private val orderUsecase: OrderUsecase) : ViewModel() {
    fun getOrderWithPizza(): Observable<List<OrderAndPizzaEntity>> {
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

    fun getNetworth(): @NonNull Observable<Int> {
        return orderUsecase.getPrice()
    }

}