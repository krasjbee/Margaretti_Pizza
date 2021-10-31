package com.example.margarettipizza.presentation.details

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.usecase.PizzaDetailsUsecase

class DetailsViewModel : ViewModel() {
    private val usecase = PizzaDetailsUsecase()

    fun getPizzaById(id: Int) = usecase(id)
}