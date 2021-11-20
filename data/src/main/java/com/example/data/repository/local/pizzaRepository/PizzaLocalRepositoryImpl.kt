package com.example.data.repository.local.pizzaRepository

import com.example.data.local.pizzaDatabase.PizzaDao
import com.example.data.remote.dto.convertToListOfPizzaEntity
import com.example.data.remote.dto.convertToPizzaEntity
import com.example.domain.entities.PizzaEntity
import com.example.domain.repository.PizzaLocalRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class PizzaLocalRepositoryImpl(private val pizzaDao: PizzaDao) :
    PizzaLocalRepository {

    override fun addToDatabase(pizzaList: List<PizzaEntity>): Completable {
        return pizzaDao.insertAll(pizzaList.convertToListOfPizzaEntity())
            .subscribeOn(Schedulers.io())
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