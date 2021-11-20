package com.example.data.local.orderDatabase

import androidx.room.*
import com.example.data.local.orderDatabase.relations.OrderWithPizza
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


@Dao
interface OrderDao {

    @Query("SELECT * FROM order_table")
    fun getOrder(): Single<List<OrderDto>>

    @Query("DELETE FROM order_table WHERE id LIKE :id")
    fun deleteEntityById(id: Int): Completable

    @Query("DELETE FROM order_table")
    fun deleteOrder(): Completable

    @Update
    fun updateEntity(dto: OrderDto): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(dto: OrderDto): Completable

    @Query("SELECT * FROM order_table WHERE id LIKE :id ")
    fun getEntityById(id: Int): Single<List<OrderDto>>

    @Transaction
    @Query("SELECT * FROM 'order_table' as orderTable INNER JOIN 'pizza_table' as pizzaTable where orderTable.id == pizzaTable.id")
    fun getOrderWithPizza(): Observable<List<OrderWithPizza>>

}