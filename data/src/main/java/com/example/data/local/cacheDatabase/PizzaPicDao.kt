package com.example.margarettipizza.data.local.cacheDatabase

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PizzaPicDao {
    @Query("SELECT * FROM cache_table")
    fun getAllCacheEntities(): Single<List<PizzaCacheDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: PizzaCacheDto): Completable

    @Delete
    fun delete(pizzaCacheDto: PizzaCacheDto): Completable

    @Query("DELETE FROM cache_table")
    fun deleteALl(): Completable

    @Insert
    fun insert(pizzaCacheDto: PizzaCacheDto): Completable

}