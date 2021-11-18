package com.example.margarettipizza.data.repository.remote

import com.example.margarettipizza.data.remote.PizzaRemoteApi
import com.example.margarettipizza.data.remote.dto.convertToPizzaEntity
import com.example.margarettipizza.domain.entities.PizzaEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PizzaRepositoryImpl @Inject constructor(private val remoteDataSource: PizzaRemoteApi) :
    PizzaRepository {

    override fun getAll(): Single<List<PizzaEntity>> {
        return remoteDataSource.getAllPizza().subscribeOn(Schedulers.io()).map {
            it.map {
                it.convertToPizzaEntity()
            }
        }
    }


    override fun getPizzaById(id: Int): Single<PizzaEntity> {
        return remoteDataSource.getPizzaById(id).subscribeOn(Schedulers.io()).map {
            it.convertToPizzaEntity()
        }
    }
}