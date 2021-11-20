package com.example.view.menu

import androidx.lifecycle.ViewModel
import com.example.domain.entities.PizzaEntity
import com.example.domain.usecase.OrderUsecase
import com.example.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.ReplaySubject
import javax.inject.Inject


class MenuViewModel @Inject constructor(
    private val pizzaUsecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {
    private val disposable = CompositeDisposable()
    val pizzaList: ReplaySubject<List<PizzaEntity>> = ReplaySubject.create()

    fun getPizzaList() {
        pizzaUsecase.getAllPizza().subscribe({
            pizzaList.onNext(it)
        }, {
            pizzaList.onError(it)
        }, disposable)
    }

    fun getFilteredList(query: String) {
        pizzaUsecase.getPizzaByName(query).subscribe({
            pizzaList.onNext(it)
        }, {
            pizzaList.onError(it)
        }, disposable)
    }


    fun getOrderNetworth(): Observable<Int> {
        return orderUsecase.getPrice()
    }

    override fun onCleared() {
        disposable.clear()
        pizzaUsecase.dispose()
        super.onCleared()
    }
}