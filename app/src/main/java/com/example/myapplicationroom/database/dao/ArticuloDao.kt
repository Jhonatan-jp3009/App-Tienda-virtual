package com.example.myapplicationroom.database.dao

import androidx.room.*
import com.example.myapplicationroom.database.entities.Articulo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticuloDao {
    @Query("SELECT * FROM articulos")
    fun getAllArticulos(): Flow<List<Articulo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticulo(articulo: Articulo)
}