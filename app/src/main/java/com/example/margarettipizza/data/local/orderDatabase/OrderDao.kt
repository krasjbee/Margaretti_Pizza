package com.example.margarettipizza.data.local.orderDatabase

import androidx.room.*
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


@Dao
interface OrderDao {

    @Query("SELECT * FROM order_table")
    fun getOrder(): Observable<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(list: List<OrderEntity>): Completable

    @Delete
    fun deleteOrderEntity(orderEntity: OrderEntity): Completable

    @Query("DELETE FROM order_table WHERE id LIKE :id")
    fun deleteEntityById(id: Int): Completable

    @Query("DELETE FROM order_table")
    fun deleteOrder(): Completable

    @Update
    fun updateEntity(entity: OrderEntity): Completable

    @Query("UPDATE order_table SET quantity =:quantity WHERE id LIKE :id")
    fun setItemQuantity(id: Int, quantity: Int): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: OrderEntity): Completable

    @Query("SELECT * FROM order_table WHERE id LIKE :id ")
    fun getEntityById(id: Int): Single<OrderEntity>

    @Transaction
    @Query("SELECT * FROM 'order_table' as orderTable INNER JOIN 'pizza_table' as pizzaTable where orderTable.id == pizzaTable.id")
    fun getOrderWithPizza(): Observable<List<OrderWithPizza>>
}