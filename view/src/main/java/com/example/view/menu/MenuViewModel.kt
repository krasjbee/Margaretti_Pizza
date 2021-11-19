package com.example.margarettipizza.presentation.menu

import androidx.lifecycle.ViewModel
import com.example.margarettipizza.domain.entities.PizzaEntity
import com.example.margarettipizza.domain.usecase.OrderUsecase
import com.example.margarettipizza.domain.usecase.PizzaUsecase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.ReplaySubject
import javax.inject.Inject

//fixme cleanup
class MenuViewModel @Inject constructor(
    private val usecase: PizzaUsecase,
    private val orderUsecase: OrderUsecase
) : ViewModel() {
    private val disposable = CompositeDisposable()
    val pizzaList: ReplaySubject<List<PizzaEntity>> = ReplaySubject.create()

    fun getPizzaList() {
        usecase.getAllPizza().subscribe({
            pizzaList.onNext(it)
        }, {
            pizzaList.onError(it)
        }, disposable)
    }

    fun getFilteredList(query: String) {
        usecase.getPizzaByName(query).subscribe({
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
        usecase.dispose()
        super.onCleared()
    }
}