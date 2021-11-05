package com.example.margarettipizza.data.local.cacheDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_table")
data class PizzaCacheEntity(
    @PrimaryKey
    val id: Int,
    val imageUrls: List<String>,
    val localImageUris: List<String>
)