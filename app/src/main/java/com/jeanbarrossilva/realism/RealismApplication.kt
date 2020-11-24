package com.jeanbarrossilva.realism

import android.app.Application
import androidx.room.Room
import com.jeanbarrossilva.realism.data.base.RealismDatabase

class RealismApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, RealismDatabase::class.java, "realism-db").build()
    }

    companion object {
        lateinit var database: RealismDatabase
    }
}