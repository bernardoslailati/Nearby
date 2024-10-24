package com.rocketseat.nlw.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.rocketseat.nlw.nearby.ui.NearbyApp
import com.rocketseat.nlw.nearby.ui.screen.HomeViewModel
import com.rocketseat.nlw.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                NearbyApp(modifier = Modifier, homeViewModel = homeViewModel)
            }
        }
    }
}