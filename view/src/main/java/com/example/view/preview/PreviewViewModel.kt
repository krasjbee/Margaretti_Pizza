package com.example.margarettipizza.presentation.preview

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.entities.OrderEntity
import com.example.margarettipizza.domain.usecase.OrderUsecase
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class PreviewViewModel @Inject constructor(
    private val usecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    fun addToCard(id: Int): Completable {
        return orderUsecase.insert(OrderEntity(id, 1))
    }


    fun getPizzaById(id: Int) = usecase.getPizzaById(id)
}