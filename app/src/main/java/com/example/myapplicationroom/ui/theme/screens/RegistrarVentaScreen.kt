package com.example.myapplicationroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationroom.database.entities.Venta
import com.example.myapplicationroom.viewModels.ArticuloViewModel
import com.example.myapplicationroom.viewModels.VentaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarVentaScreen(
    navController: NavController,
    articuloViewModel: ArticuloViewModel,
    ventaViewModel: VentaViewModel
) {
    val articulos by articuloViewModel.articulos.collectAsState()
    var selectedArticulo by remember { mutableStateOf(articulos.firstOrNull()) }
    var expanded by remember { mutableStateOf(false) }
    var grupo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    LaunchedEffect(articulos) {
        if (selectedArticulo == null) selectedArticulo = articulos.firstOrNull()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Venta") },
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
            // Dropdown de artículos
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    value = selectedArticulo?.let { "${it.codigo} - ${it.nombre}" } ?: "Sin artículos registrados",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccionar Artículo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded, { expanded = false }) {
                    articulos.forEach { art ->
                        DropdownMenuItem(
                            text = { Text("${art.codigo} - ${art.nombre}  |  $${art.precioUnitario}") },
                            onClick = { selectedArticulo = art; expanded = false }
                        )
                    }
                }
            }
            OutlinedTextField(
                grupo, { grupo = it },
                label = { Text("Número de Grupo") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                cantidad, { cantidad = it },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Preview del valor calculado
            selectedArticulo?.let { art ->
                val c = cantidad.toIntOrNull() ?: 0
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "💰 Valor a pagar: $${art.precioUnitario * c}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            if (mensaje.isNotEmpty()) Text(mensaje, color = MaterialTheme.colorScheme.primary)
            Button(
                onClick = {
                    val art = selectedArticulo
                    val g = grupo.toIntOrNull()
                    val c = cantidad.toIntOrNull()
                    if (art == null || g == null || c == null || c <= 0) {
                        mensaje = "⚠️ Verifica los datos ingresados"
                        return@Button
                    }
                    ventaViewModel.insertVenta(Venta(grupo = g, idArticulo = art.codigo, cantidad = c, valor = art.precioUnitario * c))
                    mensaje = "✅ Venta registrada correctamente"
                    grupo = ""; cantidad = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Guardar Venta") }
        }
    }
}