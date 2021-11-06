package com.example.margarettipizza.data.remote.dto


import androidx.room.Entity
import androidx.room.PrimaryKey
//import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "pizza_table")
data class PizzaDto(
//    @SerializedName("description")
    val description: String,
//    @SerializedName("id")
    @PrimaryKey
    val id: Int,
//    @SerializedName("imageUrls")
    val imageUrls: List<String>,
//    @SerializedName("name")
    val name: String,
//    @SerializedName("price")
    val price: Double
)