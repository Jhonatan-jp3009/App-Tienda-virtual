package com.example.myapplicationroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationroom.database.entities.Articulo
import com.example.myapplicationroom.viewModels.ArticuloViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarArticuloScreen(navController: NavController, viewModel: ArticuloViewModel) {
    var codigo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Artículo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(codigo, { codigo = it }, label = { Text("Código (ej: 001)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre del artículo") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(descripcion, { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(
                precio, { precio = it },
                label = { Text("Precio Unitario") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            if (mensaje.isNotEmpty()) Text(mensaje, color = MaterialTheme.colorScheme.primary)
            Button(
                onClick = {
                    val p = precio.toIntOrNull()
                    if (codigo.isBlank() || nombre.isBlank() || descripcion.isBlank() || p == null) {
                        mensaje = "⚠️ Completa todos los campos correctamente"
                        return@Button
                    }
                    viewModel.insertArticulo(Articulo(codigo.trim(), nombre.trim(), descripcion.trim(), p))
                    mensaje = "✅ Artículo guardado"
                    codigo = ""; nombre = ""; descripcion = ""; precio = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Guardar Artículo") }
        }
    }
}