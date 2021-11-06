package com.example.margarettipizza.presentation.details

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val usecase: PizzaUsecase) : ViewModel() {

    fun getPizzaById(id: Int) = usecase.getPizzaById(id)

    fun addToCart(id: Int): Unit = TODO()
}