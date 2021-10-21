package com.example.margarettipizza.domain.usecase

import com.example.margarettipizza.data.apiStub.PizzaEntity
import com.example.margarettipizza.data.repository.PizzaRepository
import com.example.margarettipizza.data.repository.PizzaRepositoryImpl

class PizzaListUsecase {
    private val repository: PizzaRepository = PizzaRepositoryImpl()

    fun getAllPizza(): List<PizzaEntity> {
        return repository.getAll()
    }

    fun getByName(query: String): List<PizzaEntity> {
        return repository.getByName(query)
    }
}