package com.example.margarettipizza.presentation.preview

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.usecase.PizzaDetailsUsecase
import javax.inject.Inject

class PreviewViewModel @Inject constructor(private val usecase: PizzaDetailsUsecase) : ViewModel() {

    fun addToCard(id: Int) {

    }


    fun getPizzaById(id: Int) = usecase(id)
}