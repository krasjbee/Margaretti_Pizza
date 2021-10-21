package com.example.margarettipizza.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.apiStub.PizzaEntity
import com.example.margarettipizza.domain.usecase.PizzaListUsecase

class MenuViewModel : ViewModel() {
    private val usecase = PizzaListUsecase()

    private val _pizzaList: MutableLiveData<List<PizzaEntity>?> =
        MutableLiveData(usecase.getAllPizza())
    val pizzaList: LiveData<List<PizzaEntity>?> = _pizzaList

    fun filterByName(query: String) {
        _pizzaList.postValue(usecase.getByName(query))
    }

}