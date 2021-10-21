package com.example.margarettipizza.domain.usecase

import com.example.margarettipizza.data.repository.PizzaRepository
import com.example.margarettipizza.data.repository.PizzaRepositoryImpl

class PizzaDetailsUsecase {
    private val repository: PizzaRepository = PizzaRepositoryImpl()
    operator fun invoke(id: Int) = requireNotNull(repository.getPizzaById(id))
}