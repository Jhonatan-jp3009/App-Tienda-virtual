package com.example.myapplicationroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationroom.ui.navigation.AppNavigation
import com.example.myapplicationroom.ui.theme.MyApplicationRoomTheme
import com.example.myapplicationroom.viewModels.ArticuloViewModel
import com.example.myapplicationroom.viewModels.VentaViewModel

class MainActivity : ComponentActivity() {
    private val articuloViewModel: ArticuloViewModel by viewModels()
    private val ventaViewModel: VentaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationRoomTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(rememberNavController(), articuloViewModel, ventaViewModel)
                }
            }
        }
    }
}