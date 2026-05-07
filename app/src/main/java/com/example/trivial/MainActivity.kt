package com.example.trivial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.trivial.navigation.NavHostContainer
import com.example.trivial.ui.theme.TrivialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrivialTheme {
                NavHostContainer()
            }
        }
    }
}
