package com.example.myapplicationroom.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "venta",
    foreignKeys = [ForeignKey(
        entity = Articulo::class,
        parentColumns = ["codigo"],
        childColumns = ["idArticulo"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Venta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val grupo: Int,
    val idArticulo: String,
    val cantidad: Int,
    val valor: Int  // Se calcula: precioUnitario * cantidad
)