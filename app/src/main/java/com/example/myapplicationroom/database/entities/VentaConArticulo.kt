package com.example.myapplicationroom.database.entities

data class VentaConArticulo(
    val id: Int,
    val grupo: Int,
    val idArticulo: String,
    val cantidad: Int,
    val valor: Int,
    val nombre: String,
    val descripcion: String,
    val precioUnitario: Int
)