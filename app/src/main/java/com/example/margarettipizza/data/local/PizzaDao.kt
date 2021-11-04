package com.example.margarettipizza.data.local

import androidx.room.*
import com.example.margarettipizza.data.remote.dto.PizzaDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PizzaDao {

    @Query("SELECT * FROM pizza_table")
    fun getAllPizza(): Single<List<PizzaDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pizzaList: PizzaDto): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pizzaDto: PizzaDto): Completable

    @Delete
    fun delete(pizza: PizzaDto): Completable

    @Update
    fun updatePizza(pizza: PizzaDto): Completable

    @Query("SELECT * FROM pizza_table WHERE name LIKE :query")
    fun getByNameMatch(query: String): Single<List<PizzaDto>>

}