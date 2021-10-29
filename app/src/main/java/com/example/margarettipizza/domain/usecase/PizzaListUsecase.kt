package com.example.margarettipizza.domain.usecase

import android.util.Log
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.data.repository.PizzaRepository
import com.example.margarettipizza.data.repository.PizzaRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PizzaListUsecase {
    private val repository: PizzaRepository = PizzaRepositoryImpl()

    fun getAllPizza(): Single<List<PizzaDto>> {
        return repository.getAll()
    }

    fun getByName(query: String): Observable<PizzaDto> {
        return repository.getAll().toObservable().flatMapIterable {
            it
        }.filter {
            it.name.contains(query)
        }
    }

    fun rxGetByName(query: Observable<String>): Observable<PizzaDto> {
//        val qwe = query.subscribe({ Log.d("qwe", "rxGetByName:$it ")})
        val map = query.flatMap { queryString ->
            repository.getAll().toObservable().flatMapIterable { it }.filter {
                it.name.contains(queryString)
            }
        }.doOnEach {
            Log.d("qwe", "rxGetByName: do on each ${it.value} ")
        }

        return map.also { Log.d("hash", "rxGetByName: ${it.hashCode()} ") }
    }
}
