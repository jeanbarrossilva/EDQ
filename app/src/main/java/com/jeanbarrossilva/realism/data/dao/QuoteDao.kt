package com.jeanbarrossilva.realism.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jeanbarrossilva.realism.data.Quote

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun all(): LiveData<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(quote: Quote)

    @Update
    fun update(quote: Quote)
}