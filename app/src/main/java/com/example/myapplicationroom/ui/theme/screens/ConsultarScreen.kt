package com.example.myapplicationroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.myapplicationroom.viewModels.VentaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultarScreen(navController: NavController, ventaViewModel: VentaViewModel) {
    var grupoInput by remember { mutableStateOf("") }
    val ventas by ventaViewModel.ventas.collectAsState()
    val total = ventas.sumOf { it.valor }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consultar Ventas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            // Búsqueda por grupo
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    grupoInput, { grupoInput = it },
                    label = { Text("Número de Grupo") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { grupoInput.toIntOrNull()?.let { ventaViewModel.buscarPorGrupo(it) } },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) { Text("Buscar") }
            }
            Spacer(Modifier.height(12.dp))

            if (ventas.isEmpty()) {
                Text("No hay ventas para este grupo.", modifier = Modifier.padding(top = 8.dp))
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(ventas) { v ->
                        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("📦 ${v.nombre}", style = MaterialTheme.typography.titleSmall)
                                Text("Descripción: ${v.descripcion}")
                                Text("Precio unitario: $${v.precioUnitario}")
                                Text("Cantidad: ${v.cantidad}")
                                Text("Valor: $${v.valor}", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
                HorizontalDivider()
                Text(
                    "TOTAL DEL GRUPO: $$total",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 12.dp).align(Alignment.End)
                )
            }
        }
    }
}