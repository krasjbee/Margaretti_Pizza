package com.example.margarettipizza.data.local.orderDatabase.relations


import androidx.room.Embedded
import androidx.room.Relation
import com.example.margarettipizza.data.local.orderDatabase.OrderEntity
import com.example.margarettipizza.data.remote.dto.PizzaDto

data class OrderWithPizza(
    @Embedded val orderEntity: OrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val pizzaEntity: PizzaDto
)