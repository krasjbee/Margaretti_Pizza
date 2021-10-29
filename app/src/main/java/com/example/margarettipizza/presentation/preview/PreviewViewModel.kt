package com.example.margarettipizza.presentation.preview

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.usecase.PizzaDetailsUsecase

class PreviewViewModel : ViewModel() {
    private val usecase = PizzaDetailsUsecase()
    fun addToCard(id: Int) {

    }


    fun getPizzaById(id: Int) = usecase(id)
}