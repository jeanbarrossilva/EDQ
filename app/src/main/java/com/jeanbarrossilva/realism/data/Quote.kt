package com.jeanbarrossilva.realism.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Quote(
    @SerializedName("_id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("content") val content: String,
    @SerializedName("author") val author: String
) : Serializable {
    val numberId =
        if (id.length == 36) UUID.fromString(id).mostSignificantBits.toInt() else id.map { it.toInt() }.joinToString(separator = "").toInt()

    var isRead = false
    var isFavorite = false

    companion object {
        fun mine(content: String) = Quote(content = content, author = "Jean Silva")
    }
}