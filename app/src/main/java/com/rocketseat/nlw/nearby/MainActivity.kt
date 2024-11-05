package com.rocketseat.nlw.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.rocketseat.nlw.nearby.ui.NearbyApp
import com.rocketseat.nlw.nearby.ui.screen.home.HomeViewModel
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsViewModel
import com.rocketseat.nlw.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    private val marketDetailsViewModel by viewModels<MarketDetailsViewModel>(
        factoryProducer = { ViewModelProvider.AndroidViewModelFactory(application) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                NearbyApp(
                    modifier = Modifier,
                    homeViewModel = homeViewModel,
                    marketDetailsViewModel = marketDetailsViewModel
                )
            }
        }
    }
}