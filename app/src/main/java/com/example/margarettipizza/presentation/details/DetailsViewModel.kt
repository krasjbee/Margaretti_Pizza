package com.example.margarettipizza.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.apiStub.PizzaEntity
import com.example.margarettipizza.domain.usecase.PizzaDetailsUsecase

class DetailsViewModel : ViewModel() {
    private val usecase = PizzaDetailsUsecase()

    private val _pizza: MutableLiveData<PizzaEntity> = MutableLiveData()
    val pizza: LiveData<PizzaEntity> = _pizza

    fun getPizzaById(id: Int) {
        _pizza.postValue(usecase(id))
    }
}