package com.example.domain.repository

import com.example.domain.entities.PizzaEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PizzaLocalRepository {

    fun addToDatabase(pizzaList: List<PizzaEntity>): Completable

    fun getAllPizza(): Single<List<PizzaEntity>>

    fun getPizzaById(id: Int): Single<PizzaEntity>

    fun getPizzaByName(query: String): Single<List<PizzaEntity>>
}