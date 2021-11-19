package com.example.data.local.typeconverter

//import com.google.gson.Gson
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListTypeConverter {

    @TypeConverter
    fun listToJsonString(entity: List<String>): String = Json.encodeToString(entity)

    @TypeConverter
    fun jsonStringToList(string: String): List<String> = Json.decodeFromString(string)
}