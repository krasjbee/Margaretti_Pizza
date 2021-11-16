package com.example.margarettipizza.presentation.menu

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.domain.usecase.OrderUsecase
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

//fixme cleanup
class MenuViewModel @Inject constructor(
    private val usecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {

    val pizzaList get() = usecase.getAllPizza()

    fun getFilteredList(query: String): Single<List<PizzaDto>> {
        return usecase.getPizzaByName(query)
    }

    fun getOrderNetworth(): Observable<Int> {
        return orderUsecase.getPrice()
    }

    override fun onCleared() {
        usecase.dispose()
        super.onCleared()
    }
}