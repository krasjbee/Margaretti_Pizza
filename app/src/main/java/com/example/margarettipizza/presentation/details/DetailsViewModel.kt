package com.example.margarettipizza.presentation.details

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.domain.usecase.OrderUsecase
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val usecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    fun getPizzaById(id: Int) = usecase.getPizzaById(id)

    fun addToCart(id: Int): Completable = orderUsecase.insert(OrderEntity(id, 1))
}