package com.example.data.repository.remote

import com.example.data.remote.PizzaRemoteApi
import com.example.data.remote.dto.convertToOrderRemoteDto
import com.example.data.remote.dto.convertToPizzaEntity
import com.example.domain.entities.OrderEntity
import com.example.domain.entities.PizzaEntity
import com.example.domain.repository.PizzaRemoteRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class PizzaRemoteRepositoryImpl constructor(private val remoteDataSource: PizzaRemoteApi) :
    PizzaRemoteRepository {

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

    override fun postOrder(order: List<OrderEntity>): Completable {
        return remoteDataSource.postOrder(order.map {
            it.convertToOrderRemoteDto()
        })
    }
}