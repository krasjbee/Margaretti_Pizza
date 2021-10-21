package com.example.margarettipizza.data.repository

import com.example.margarettipizza.data.apiStub.PizzaEntity

interface PizzaRepository {
    // TODO: 19.10.2021 add documentation
    /**
     *
     */
    fun getAll(): List<PizzaEntity>

    /**
     *
     */
    fun getByName(query: String): List<PizzaEntity>

    fun getPizzaById(id: Int): PizzaEntity?

}