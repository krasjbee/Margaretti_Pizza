package com.example.margarettipizza.data.repository.local

import com.example.margarettipizza.data.local.pizzaDatabase.PizzaDao
import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PizzaLocalRepositoryImpl @Inject constructor(private val pizzaDao: PizzaDao) :
    PizzaLocalRepository {

    override fun addToDatabase(pizzaList: List<PizzaDto>): Completable {
        return pizzaDao.insertAll(pizzaList).subscribeOn(Schedulers.io())
    }

    override fun removePizza(pizza: PizzaDto): Completable {
        return pizzaDao.delete(pizza).subscribeOn(Schedulers.io())
    }

    override fun getAllPizza(): Single<List<PizzaDto>> {
        return pizzaDao.getAllPizza().subscribeOn(Schedulers.io())
    }

    override fun getPizzaById(id: Int): Single<PizzaDto> {
        return pizzaDao.getPizzaById(id).subscribeOn(Schedulers.io())
    }

    override fun getPizzaByName(query: String): Single<List<PizzaDto>> {
        return pizzaDao.getByNameMatch("%$query%").subscribeOn(Schedulers.io())
    }

}