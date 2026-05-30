package com.makerledger.util
import com.google.gson.Gson
import java.io.File

object JsonExporter {
    private val gson = Gson()

    fun <T> exportToJson(items: List<T>, file: File) {
        file.writeText(gson.toJson(items))
    }
}
