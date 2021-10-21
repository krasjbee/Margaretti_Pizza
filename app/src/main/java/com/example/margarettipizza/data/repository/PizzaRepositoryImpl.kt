package com.example.margarettipizza.data.repository

import com.example.margarettipizza.data.apiStub.PizzaDatabase
import com.example.margarettipizza.data.apiStub.PizzaEntity

//it's better to inject dao here , but for now we can use dao like object
class PizzaRepositoryImpl : PizzaRepository {

    override fun getAll(): List<PizzaEntity> = PizzaDatabase.pizzaDao.getAll()

    override fun getByName(query: String): List<PizzaEntity> =
        PizzaDatabase.pizzaDao.query { pizzaList ->
            pizzaList.filter { pizza ->
                pizza.name.contains(query)
//                        ||pizza.description.contains(query)
            }
        }

    override fun getPizzaById(id: Int): PizzaEntity? {
        return PizzaDatabase.pizzaDao.getById(id)
    }
}