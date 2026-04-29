package com.example.myapplicationroom.database.config

import android.content.Context
import androidx.room.Room

object ConexionDB {
    @Volatile
    private var INSTANCE: AppDataBase? = null

    fun getDatabase(context: Context): AppDataBase {
        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "tienda_database"
            ).build().also { INSTANCE = it }
        }
    }
}