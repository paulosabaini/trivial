package com.example.trivial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trivial.home.navigation.HomeRoute
import com.example.trivial.navigation.NavHostContainer
import com.example.trivial.ui.components.TrivialTopAppBar
import com.example.trivial.ui.theme.TrivialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrivialTheme {
                val navController = rememberNavController()
                var showBack by remember { mutableStateOf(false) }
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                showBack = currentDestination?.hasRoute<HomeRoute>() == false

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TrivialTopAppBar(
                            title = "Trivial",
                            showBack = showBack,
                            onBack = { navController.popBackStack() })
                    },
                ) { innerPadding ->
                    NavHostContainer(
                        navController = navController,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}
