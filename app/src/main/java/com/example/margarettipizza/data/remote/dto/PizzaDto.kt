package com.example.margarettipizza.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PizzaDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)