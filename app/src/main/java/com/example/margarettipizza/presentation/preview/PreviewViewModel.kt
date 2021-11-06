package com.example.margarettipizza.presentation.preview

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import javax.inject.Inject

class PreviewViewModel @Inject constructor(private val usecase: PizzaUsecase) : ViewModel() {

    fun addToCard(id: Int) {

    }


    fun getPizzaById(id: Int) = usecase.getPizzaById(id)
}