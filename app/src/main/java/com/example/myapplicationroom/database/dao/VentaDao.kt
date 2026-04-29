package com.example.myapplicationroom.database.dao

import androidx.room.*
import com.example.myapplicationroom.database.entities.Venta
import com.example.myapplicationroom.database.entities.VentaConArticulo
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    @Insert
    suspend fun insertVenta(venta: Venta)

    @Query("""
        SELECT v.id, v.grupo, v.idArticulo, v.cantidad, v.valor,
               a.nombre, a.descripcion, a.precioUnitario
        FROM venta v
        INNER JOIN articulos a ON v.idArticulo = a.codigo
        WHERE v.grupo = :grupo
    """)
    fun getVentasConArticuloByGrupo(grupo: Int): Flow<List<VentaConArticulo>>
}