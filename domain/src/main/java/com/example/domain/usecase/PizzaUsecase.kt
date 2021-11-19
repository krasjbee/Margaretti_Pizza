package com.example.domain.usecase

import com.example.domain.entities.PizzaEntity
import com.example.domain.repository.PizzaLocalRepository
import com.example.domain.repository.PizzaRemoteRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.disposables.CompositeDisposable


class PizzaUsecase constructor(
    private val remoteRemoteRepository: PizzaRemoteRepository,
    private val localRepository: PizzaLocalRepository
) {

    private val listDisposable = CompositeDisposable()

    fun getAllPizza(): Single<List<PizzaEntity>> {
        return remoteRemoteRepository.getAll().flatMapCompletable {
            localRepository.addToDatabase(it)
        }.andThen(SingleSource { obs ->
            localRepository.getAllPizza().subscribe({ obs.onSuccess(it) }, {}, listDisposable)
        })
    }

    fun getPizzaByName(query: String): Single<List<PizzaEntity>> {
        return localRepository.getPizzaByName(query)
    }

    fun getPizzaById(id: Int): Single<PizzaEntity> {
        return localRepository.getPizzaById(id)
    }

    fun dispose() {
        listDisposable.clear()
    }

}