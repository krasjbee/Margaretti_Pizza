package com.example.view.cart

import androidx.lifecycle.ViewModel
import com.example.domain.entities.OrderAndPizzaEntity
import com.example.domain.usecase.OrderUsecase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class CartViewModel @Inject constructor(private val orderUsecase: OrderUsecase) : ViewModel() {

    private val disposable = CompositeDisposable()

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

    fun getNetworth(): Observable<Int> {
        return orderUsecase.getPrice()
    }

    fun postOrder() {
        disposable.add(
            orderUsecase.postOrder().subscribe()
        )
    }

    override fun onCleared() {
        orderUsecase.dispose()
        disposable.clear()
        super.onCleared()
    }

}