package com.example.data.local.orderDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.remote.dto.OrderRemoteDto
import com.example.domain.entities.OrderEntity
import kotlinx.serialization.Serializable

@Serializable
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

fun OrderDto.convertToOrderRemoteDto(): OrderRemoteDto {
    return OrderRemoteDto(this.id, this.quantity)
}