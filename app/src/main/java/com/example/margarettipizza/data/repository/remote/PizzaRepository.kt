package com.example.margarettipizza.data.repository.remote

import com.example.margarettipizza.domain.entities.PizzaEntity
import io.reactivex.rxjava3.core.Single

interface PizzaRepository {
    // TODO: 19.10.2021 add documentation
    /**
     *
     */
    fun getAll(): Single<List<PizzaEntity>>


    fun getPizzaById(id: Int): Single<PizzaEntity>

}