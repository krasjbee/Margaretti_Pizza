package com.example.data.local.orderDatabase.relations


import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.local.orderDatabase.OrderDto
import com.example.data.local.orderDatabase.convertToOrderEntity
import com.example.data.remote.dto.PizzaDto
import com.example.data.remote.dto.convertToPizzaEntity
import com.example.domain.entities.OrderAndPizzaEntity

data class OrderWithPizza(
    @Embedded val orderDto: OrderDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val pizzaDto: PizzaDto
)

fun OrderWithPizza.convertToOrderAndPizzaEntity(): OrderAndPizzaEntity {
    return OrderAndPizzaEntity(
        this.orderDto.convertToOrderEntity(),
        this.pizzaDto.convertToPizzaEntity(),
    )
}