package com.example.margarettipizza.data.local.orderDatabase.relations


import androidx.room.Embedded
import androidx.room.Relation
import com.example.margarettipizza.data.local.orderDatabase.OrderDto
import com.example.margarettipizza.data.local.orderDatabase.convertToOrderEntity
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.data.remote.dto.convertToPizzaEntity
import com.example.margarettipizza.domain.entities.OrderAndPizzaEntity

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