package com.example.margarettipizza.data.repository.local.pizzaRepository

import com.example.margarettipizza.data.local.pizzaDatabase.PizzaDao
import com.example.margarettipizza.data.remote.dto.convertToListOfPizzaEntity
import com.example.margarettipizza.data.remote.dto.convertToPizzaDto
import com.example.margarettipizza.data.remote.dto.convertToPizzaEntity
import com.example.margarettipizza.domain.entities.PizzaEntity
import com.example.margarettipizza.domain.repository.PizzaLocalRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PizzaLocalRepositoryImpl @Inject constructor(private val pizzaDao: PizzaDao) :
    PizzaLocalRepository {

    override fun addToDatabase(pizzaList: List<PizzaEntity>): Completable {
        return pizzaDao.insertAll(pizzaList.convertToListOfPizzaEntity())
            .subscribeOn(Schedulers.io())
    }

    override fun removePizza(pizza: PizzaEntity): Completable {
        return pizzaDao.delete(pizza.convertToPizzaDto()).subscribeOn(Schedulers.io())
    }

    override fun getAllPizza(): Single<List<PizzaEntity>> {
        return pizzaDao.getAllPizza().subscribeOn(Schedulers.io()).map {
            it.map {
                it.convertToPizzaEntity()
            }
        }
    }

    override fun getPizzaById(id: Int): Single<PizzaEntity> {
        return pizzaDao.getPizzaById(id).subscribeOn(Schedulers.io()).map {
            it.convertToPizzaEntity()
        }
    }

    override fun getPizzaByName(query: String): Single<List<PizzaEntity>> {
        return pizzaDao.getByNameMatch("%$query%").subscribeOn(Schedulers.io()).map {
            it.map {
                it.convertToPizzaEntity()
            }
        }
    }

}