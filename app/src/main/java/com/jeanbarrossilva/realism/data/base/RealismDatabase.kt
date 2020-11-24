package com.jeanbarrossilva.realism.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeanbarrossilva.realism.data.Quote
import com.jeanbarrossilva.realism.data.dao.QuoteDao

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class RealismDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}