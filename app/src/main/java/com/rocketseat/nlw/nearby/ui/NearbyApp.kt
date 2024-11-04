package com.rocketseat.nlw.nearby.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.ui.routes.Home
import com.rocketseat.nlw.nearby.ui.routes.Splash
import com.rocketseat.nlw.nearby.ui.routes.Welcome
import com.rocketseat.nlw.nearby.ui.screen.home.HomeScreen
import com.rocketseat.nlw.nearby.ui.screen.home.HomeViewModel
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsScreen
import com.rocketseat.nlw.nearby.ui.screen.SplashScreen
import com.rocketseat.nlw.nearby.ui.screen.WelcomeScreen
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsViewModel

@Composable
fun NearbyApp(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    marketDetailsViewModel: MarketDetailsViewModel
) {
    val navController = rememberNavController()

    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val marketDetailsUiState by marketDetailsViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateToWelcome = {
                    navController.navigate(Welcome)
                }
            )
        }

        composable<Welcome> {
            WelcomeScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateToMap = {
                    navController.navigate(Home)
                }
            )
        }

        composable<Home> {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = homeUiState,
                viewModel = homeViewModel,
                onNavigateToMarketDetails = { selectedMarket ->
                    navController.navigate(selectedMarket)
                }
            )
        }

        composable<Market> { backStackEntry ->
            val market: Market = backStackEntry.toRoute()

            MarketDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = marketDetailsUiState,
                viewModel = marketDetailsViewModel,
                market = market,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onScanQRCode = {
                }
            )
        }
    }
}