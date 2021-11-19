package com.example.view.details

import androidx.lifecycle.ViewModel
import com.example.domain.entities.OrderEntity
import com.example.domain.usecase.OrderUsecase
import com.example.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val usecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun getPizzaById(id: Int) = usecase.getPizzaById(id)

    fun addToCart(id: Int) {
        disposable.add(orderUsecase.insert(OrderEntity(id, 1)).subscribe())
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}