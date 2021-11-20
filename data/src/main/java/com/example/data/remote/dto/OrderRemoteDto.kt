package com.example.data.remote.dto

import com.example.domain.entities.OrderEntity
import kotlinx.serialization.Serializable

@Serializable
data class OrderRemoteDto(val pizzaId: Int, val quantity: Int)

fun OrderEntity.convertToOrderRemoteDto(): OrderRemoteDto {
    return OrderRemoteDto(this.pizzaId, this.quantity)
}
