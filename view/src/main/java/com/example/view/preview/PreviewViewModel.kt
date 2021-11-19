package com.example.margarettipizza.presentation.preview

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.entities.OrderEntity
import com.example.margarettipizza.domain.usecase.OrderUsecase
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PreviewViewModel @Inject constructor(
    private val usecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun addToCart(id: Int) {
        disposable.add(orderUsecase.insert(OrderEntity(id, 1)).subscribe())
    }

    fun getPizzaById(id: Int) = usecase.getPizzaById(id)

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}