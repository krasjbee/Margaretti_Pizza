package com.example.margarettipizza.domain.usecase

import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.data.repository.local.pizzaRepository.PizzaLocalRepository
import com.example.margarettipizza.data.repository.remote.PizzaRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PizzaUsecase @Inject constructor(
    private val remoteRepository: PizzaRepository,
    private val localRepository: PizzaLocalRepository
) {

    private val listDisposable = CompositeDisposable()

    fun getAllPizza(): Single<List<PizzaDto>> {
        remoteRepository.getAll()
            .subscribe({
                listDisposable.add(localRepository.addToDatabase(it).subscribe())
            }, {
            }, listDisposable)
        return localRepository.getAllPizza()
    }

    fun getPizzaByName(query: String): Single<List<PizzaDto>> {
        return localRepository.getPizzaByName(query)
    }

    fun getPizzaById(id: Int): Single<PizzaDto> {
        return localRepository.getPizzaById(id)
    }

    fun dispose() {
        listDisposable.clear()
    }

}