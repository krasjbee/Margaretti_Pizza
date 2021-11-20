package com.example.view.preview

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.OrderUsecase
import com.example.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PreviewViewModel @Inject constructor(
    private val usecasePizza: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun addToCart(id: Int) {
        disposable.add(orderUsecase.incrementQuantity(id).subscribe())
    }

    fun getPizzaById(id: Int) = usecasePizza.getPizzaById(id)

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}