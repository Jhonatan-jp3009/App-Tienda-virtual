package com.example.myapplicationroom.repositories

import com.example.myapplicationroom.database.dao.VentaDao
import com.example.myapplicationroom.database.entities.Venta
import com.example.myapplicationroom.database.entities.VentaConArticulo
import kotlinx.coroutines.flow.Flow

class VentaRepository(private val dao: VentaDao) {
    fun getVentasByGrupo(grupo: Int): Flow<List<VentaConArticulo>> =
        dao.getVentasConArticuloByGrupo(grupo)

    suspend fun insertVenta(venta: Venta) = dao.insertVenta(venta)
}