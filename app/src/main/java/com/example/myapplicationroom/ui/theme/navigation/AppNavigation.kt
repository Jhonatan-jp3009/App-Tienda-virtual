package com.example.myapplicationroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplicationroom.ui.screens.*
import com.example.myapplicationroom.viewModels.ArticuloViewModel
import com.example.myapplicationroom.viewModels.VentaViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    articuloViewModel: ArticuloViewModel,
    ventaViewModel: VentaViewModel
) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("registrar_articulo") {
            RegistrarArticuloScreen(navController, articuloViewModel)
        }
        composable("registrar_venta") {
            RegistrarVentaScreen(navController, articuloViewModel, ventaViewModel)
        }
        composable("consultar") {
            ConsultarScreen(navController, ventaViewModel)
        }
    }
}