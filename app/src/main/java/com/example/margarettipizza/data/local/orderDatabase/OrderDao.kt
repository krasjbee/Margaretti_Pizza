package com.example.margarettipizza.data.local.orderDatabase

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


@Dao
interface OrderDao {

    @Query("SELECT * FROM order_table")
    fun getOrder(): Observable<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(list: List<OrderEntity>): Completable

    @Delete
    fun deleteOrderEntity(orderEntity: OrderEntity): Completable

    @Query("DELETE FROM order_table")
    fun deleteOrder(): Completable

    @Query("UPDATE order_table SET quantity =:quantity WHERE id LIKE :id")
    fun setItemQuantity(id: Int, quantity: Int): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: OrderEntity): Completable
}