package com.example.margarettipizza.data.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListTypeConverter {

    @TypeConverter
    fun listToJsonString(entity: List<String>): String = Gson().toJson(entity)

    @TypeConverter
    fun jsonStringToList(string: String): List<String> {

        val type = emptyList<String>().javaClass
        return Gson().fromJson(string, type)
    }
}