package com.example.margarettipizza.domain.repository

import com.example.margarettipizza.domain.entities.PizzaEntity
import io.reactivex.rxjava3.core.Single

interface PizzaRemoteRepository {
    // TODO: 19.10.2021 add documentation
    /**
     *
     */
    fun getAll(): Single<List<PizzaEntity>>


    fun getPizzaById(id: Int): Single<PizzaEntity>

}