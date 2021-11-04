package com.example.margarettipizza.presentation.details

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.usecase.PizzaDetailsUsecase
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val usecase: PizzaDetailsUsecase) : ViewModel() {

    fun getPizzaById(id: Int) = usecase(id)
}