package com.example.margarettipizza.data.repository.local

import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PizzaLocalRepository {

    fun addToDatabase(pizzaList: List<PizzaDto>): Completable

    fun removePizza(pizza: PizzaDto): Completable

    fun getAllPizza(): Single<List<PizzaDto>>

    fun getPizzaById(id: Int): Single<PizzaDto>

    fun getPizzaByName(query: String): Single<List<PizzaDto>>
}