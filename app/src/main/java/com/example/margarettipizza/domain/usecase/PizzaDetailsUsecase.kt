package com.example.margarettipizza.domain.usecase

//import com.example.margarettipizza.data.repository.PizzaRepositoryImpl
import com.example.margarettipizza.data.repository.PizzaRepositoryCopy
import com.example.margarettipizza.data.repository.RxRepo

class PizzaDetailsUsecase {
    private val repository: PizzaRepositoryCopy = RxRepo()

    // FIXME: 28.10.2021 change from fixed list
    operator fun invoke(id: Int) = repository.getPizzaById(id)
}