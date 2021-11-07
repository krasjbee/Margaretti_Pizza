package com.example.margarettipizza.presentation.cart

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import com.example.margarettipizza.domain.usecase.OrderUsecase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CartViewModel @Inject constructor(private val orderUsecase: OrderUsecase) : ViewModel() {
    fun getOrderWithPizza(): Observable<List<OrderWithPizza>> {
        return orderUsecase.getOrderWithPizza()
    }
}