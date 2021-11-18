package com.example.margarettipizza.data.repository.local.pizzaRepository

import com.example.margarettipizza.domain.entities.PizzaEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PizzaLocalRepository {

    fun addToDatabase(pizzaList: List<PizzaEntity>): Completable

    fun removePizza(pizza: PizzaEntity): Completable

    fun getAllPizza(): Single<List<PizzaEntity>>

    fun getPizzaById(id: Int): Single<PizzaEntity>

    fun getPizzaByName(query: String): Single<List<PizzaEntity>>
}