package com.example.margarettipizza.domain.usecase

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

    fun rxGetByName(query: Observable<String>): Observable<PizzaDto> {
        val map = query.flatMap { queryString ->
            repository.getAll().toObservable().flatMapIterable { it }.filter {
                it.name.contains(queryString)
            }
        }
        return map
    }
}
