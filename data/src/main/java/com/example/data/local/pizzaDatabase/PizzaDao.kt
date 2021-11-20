package com.example.data.local.pizzaDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PizzaDao {

    @Query("SELECT * FROM pizza_table")
    fun getAllPizza(): Single<List<PizzaDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<PizzaDto>): Completable

    @Query("SELECT * FROM pizza_table WHERE name LIKE :query")
    fun getByNameMatch(query: String): Single<List<PizzaDto>>

    @Query("SELECT * FROM pizza_table WHERE id LIKE :id")
    fun getPizzaById(id: Int): Single<PizzaDto>

}