package com.example.margarettipizza.data.local.orderDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.margarettipizza.domain.entities.OrderEntity

@Entity(tableName = "order_table")
data class OrderDto(
    @PrimaryKey
    val id: Int,
    val quantity: Int
)

fun OrderDto.convertToOrderEntity(): OrderEntity {
    return OrderEntity(this.id, this.quantity)
}

fun OrderEntity.convertToOrderDto(): OrderDto {
    return OrderDto(this.pizzaId, this.quantity)
}