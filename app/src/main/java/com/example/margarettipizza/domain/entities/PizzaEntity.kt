package com.example.margarettipizza.domain.entities

data class PizzaEntity(
    val description: String,
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val price: String
)