package com.jeanbarrossilva.realism.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "content") @SerializedName("content") val content: String,
    @ColumnInfo(name = "author") @SerializedName("author") val author: String,
    @ColumnInfo(name = "read") var isRead: Boolean = false,
    @ColumnInfo(name = "favorite") var isFavorite: Boolean = false
) : Serializable {
    companion object {
        fun default(content: String) = Quote(content = content, author = "Jean Silva")
    }
}