package com.example.margarettipizza.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.margarettipizza.domain.entities.PizzaEntity
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
@Entity(tableName = "pizza_table")
data class PizzaDto(
    val description: String,
    @PrimaryKey
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val price: Double
)

fun PizzaDto.convertToPizzaEntity(): PizzaEntity {
    return PizzaEntity(
        this.description,
        this.id,
        this.imageUrls,
        this.name,
        "${this.price.roundToInt()}₽"
    )
}

fun PizzaEntity.convertToPizzaDto(): PizzaDto {
    return PizzaDto(
        this.description,
        this.id,
        this.imageUrls,
        this.name,
        this.price.dropLast(1).toDouble()
    )
}

fun List<PizzaEntity>.convertToListOfPizzaEntity(): List<PizzaDto> {
    return this.map {
        it.convertToPizzaDto()
    }
}