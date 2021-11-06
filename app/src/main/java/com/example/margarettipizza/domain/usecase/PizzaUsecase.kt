package com.example.margarettipizza.domain.usecase

import android.util.Log
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.data.repository.PizzaLocalRepository
import com.example.margarettipizza.data.repository.PizzaRepository
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
            .doOnSubscribe {
                Log.d("qwe", "getAllPizza: on get inet ")
            }
            .subscribe({
                localRepository.addToDatabase(it).subscribe({ Log.d("qwe", "compl on suc ") },
                    { Log.d("qwe", "compl on err ${it.message}") })
            }, {
                Log.d("qwe", "getAllPizza: error ")
                //fixme do on error
            }, listDisposable)
        return localRepository.getAllPizza()
            .doOnError { Log.d("qwe", "getAllPizza err: do on error ${it.message} ") }
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