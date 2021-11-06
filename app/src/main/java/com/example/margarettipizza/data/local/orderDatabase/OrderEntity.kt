package com.example.margarettipizza.data.local.orderDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
data class OrderEntity(
    @PrimaryKey
    val id: Int,
    val quantity: Int
)