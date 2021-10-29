package com.example.margarettipizza.data.repository

import com.example.margarettipizza.data.apiStub.PizzaEntity
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.utils.NetworkResource
import io.reactivex.rxjava3.core.Observable

interface PizzaRepository {
    // TODO: 19.10.2021 add documentation
    /**
     *
     */
    fun getAll(): Observable<NetworkResource<List<PizzaDto>>>

    /**
     *
     */
    fun getByName(query: String): Observable<NetworkResource<List<PizzaDto>>>

    fun getPizzaById(id: Int): PizzaEntity?

}