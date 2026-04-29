package com.example.myapplicationroom.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationroom.database.config.ConexionDB
import com.example.myapplicationroom.database.entities.Articulo
import com.example.myapplicationroom.repositories.ArticuloRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArticuloViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ArticuloRepository(
        ConexionDB.getDatabase(application).articuloDao()
    )

    val articulos: StateFlow<List<Articulo>> = repository.allArticulos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertArticulo(articulo: Articulo) = viewModelScope.launch {
        repository.insertArticulo(articulo)
    }
}