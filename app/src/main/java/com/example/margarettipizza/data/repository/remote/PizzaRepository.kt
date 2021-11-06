package com.example.margarettipizza.data.repository.remote

import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Single

interface PizzaRepository {
    // TODO: 19.10.2021 add documentation
    /**
     *
     */
    fun getAll(): Single<List<PizzaDto>>


    fun getPizzaById(id: Int): Single<PizzaDto>

}