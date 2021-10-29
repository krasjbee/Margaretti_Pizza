package com.example.margarettipizza.presentation.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.domain.usecase.PizzaListUsecase
import io.reactivex.rxjava3.core.Observable

class MenuViewModel : ViewModel() {
    private val usecase = PizzaListUsecase()

    var pizzaList = usecase.getAllPizza()
    var filtredList: Observable<PizzaDto>? = null

    fun getPizzaList() {
        pizzaList = usecase.getAllPizza()

        Log.d("hash", "filterByName:${pizzaList.hashCode()} ")
    }

    fun filterByName(query: String) {
        val queryStream = Observable.create<String> {
            it.onNext(query)
            //fixme kostil'
            it.onComplete()
        }
        filtredList = usecase.rxGetByName(queryStream)
        filtredList!!.subscribe {
            Log.d("qwe", "filterByName: viewmodel $it ")
        }
    }
}