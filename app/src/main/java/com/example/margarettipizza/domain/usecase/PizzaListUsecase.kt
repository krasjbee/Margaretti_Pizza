package com.example.margarettipizza.domain.usecase

import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.data.repository.PizzaRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PizzaListUsecase @Inject constructor(private val remoteRepository: PizzaRepository) {


    fun getAllPizza(): Single<List<PizzaDto>> {
        return this.remoteRepository.getAll()
    }

    fun rxGetByName(query: Observable<String>): Observable<PizzaDto> {
        val map = query.flatMap { queryString ->
            this.remoteRepository.getAll().toObservable().flatMapIterable { it }.filter {
                it.name.contains(queryString)
            }
        }
        return map
    }
}
