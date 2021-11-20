package com.example.view.details

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.OrderUsecase
import com.example.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val pizzaUsecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun getPizzaById(id: Int) = pizzaUsecase.getPizzaById(id)

    fun addToCart(id: Int) {
        disposable.add(orderUsecase.incrementQuantity(id).subscribe())
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}