package com.example.margarettipizza.domain.usecase

//import com.example.margarettipizza.data.repository.PizzaRepositoryImpl
import com.example.margarettipizza.data.repository.PizzaRepository
import javax.inject.Inject

class PizzaDetailsUsecase @Inject constructor(private val repository: PizzaRepository) {
    operator fun invoke(id: Int) = repository.getPizzaById(id)
}