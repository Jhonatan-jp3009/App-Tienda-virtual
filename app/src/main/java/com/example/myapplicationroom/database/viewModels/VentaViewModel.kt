package com.example.myapplicationroom.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationroom.database.config.ConexionDB
import com.example.myapplicationroom.database.entities.Venta
import com.example.myapplicationroom.database.entities.VentaConArticulo
import com.example.myapplicationroom.repositories.VentaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VentaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VentaRepository(
        ConexionDB.getDatabase(application).ventaDao()
    )

    private val _ventas = MutableStateFlow<List<VentaConArticulo>>(emptyList())
    val ventas: StateFlow<List<VentaConArticulo>> = _ventas

    fun buscarPorGrupo(grupo: Int) = viewModelScope.launch {
        repository.getVentasByGrupo(grupo).collect { _ventas.value = it }
    }

    fun insertVenta(venta: Venta) = viewModelScope.launch {
        repository.insertVenta(venta)
    }
}