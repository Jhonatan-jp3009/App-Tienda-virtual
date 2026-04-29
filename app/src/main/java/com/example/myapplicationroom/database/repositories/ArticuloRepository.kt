package com.example.myapplicationroom.repositories

import com.example.myapplicationroom.database.dao.ArticuloDao
import com.example.myapplicationroom.database.entities.Articulo
import kotlinx.coroutines.flow.Flow

class ArticuloRepository(private val dao: ArticuloDao) {
    val allArticulos: Flow<List<Articulo>> = dao.getAllArticulos()

    suspend fun insertArticulo(articulo: Articulo) = dao.insertArticulo(articulo)
}