package com.rocketseat.nlw.nearby.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.ui.routes.Home
import com.rocketseat.nlw.nearby.ui.routes.Splash
import com.rocketseat.nlw.nearby.ui.routes.Welcome
import com.rocketseat.nlw.nearby.ui.screen.HomeScreen
import com.rocketseat.nlw.nearby.ui.screen.MarketDetailsScreen
import com.rocketseat.nlw.nearby.ui.screen.SplashScreen
import com.rocketseat.nlw.nearby.ui.screen.WelcomeScreen

@Composable
fun NearbyApp(modifier: Modifier) {
    val navController = rememberNavController()

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
                onNavigateToMarketDetails = { selectedMarket ->
                    navController.navigate(selectedMarket)
                }
            )
        }

        composable<Market> { backStackEntry ->
            val market: Market = backStackEntry.toRoute()

            MarketDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                market = market,
                coupons = listOf("AM4345T1", "BM4345T2"),
                onNavigateBack = {
                    navController.popBackStack()
                },
                onScanQRCode = {
                }
            )
        }
    }
}