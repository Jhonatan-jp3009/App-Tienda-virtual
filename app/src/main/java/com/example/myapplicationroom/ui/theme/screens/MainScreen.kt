package com.example.myapplicationroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🛒 Tienda Virtual", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(40.dp))
        Button(onClick = { navController.navigate("consultar") }, Modifier.fillMaxWidth()) {
            Text("Consultar Ventas por Grupo")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("registrar_venta") }, Modifier.fillMaxWidth()) {
            Text("Registrar Venta")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("registrar_articulo") }, Modifier.fillMaxWidth()) {
            Text("Registrar Artículo")
        }
    }
}