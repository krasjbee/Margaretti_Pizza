package com.example.margarettipizza.data.repository

import com.example.margarettipizza.data.apiStub.PizzaEntity
import com.example.margarettipizza.data.remote.NetworkModule
import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RxRepo : PizzaRepositoryCopy {

    private val remoteDataSource = NetworkModule.retrofit

    override fun getAll(): Single<List<PizzaDto>> {
        return remoteDataSource.getAllPizza().subscribeOn(Schedulers.io())
    }

    override fun getByName(query: String): Single<List<PizzaDto>> {
        return remoteDataSource.getAllPizza().subscribeOn(Schedulers.io())
    }

    override fun getPizzaById(id: Int): PizzaEntity? {
        TODO("Not yet implemented")
    }
}