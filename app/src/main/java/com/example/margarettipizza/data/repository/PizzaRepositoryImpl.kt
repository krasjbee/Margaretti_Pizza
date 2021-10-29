package com.example.margarettipizza.data.repository

import com.example.margarettipizza.data.remote.NetworkModule
import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PizzaRepositoryImpl : PizzaRepository {

    private val remoteDataSource = NetworkModule.retrofit

    override fun getAll(): Single<List<PizzaDto>> {
        return remoteDataSource.getAllPizza().subscribeOn(Schedulers.io())
    }

    override fun getByName(query: String): Single<List<PizzaDto>> {
        return remoteDataSource.getAllPizza().subscribeOn(Schedulers.io())
    }

    override fun getPizzaById(id: Int): Single<PizzaDto> {
        return remoteDataSource.getPizzaById(id).subscribeOn(Schedulers.io())
    }
}