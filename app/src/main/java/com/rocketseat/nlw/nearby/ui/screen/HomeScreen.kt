package com.rocketseat.nlw.nearby.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.common.network.ResultState
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.mock.mockMarkets
import com.rocketseat.nlw.nearby.data.model.mock.mockUserLocation
import com.rocketseat.nlw.nearby.ui.component.category.CategoryFilterChipList
import com.rocketseat.nlw.nearby.ui.component.market.MarketCardList
import com.rocketseat.nlw.nearby.ui.theme.Gray100
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    onNavigateToMarketDetails: (Market) -> Unit
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val marketsBottomSheetState =
        rememberBottomSheetScaffoldState(bottomSheetState = rememberModalBottomSheetState())
    var isMarketsBottomSheetOpened by remember { mutableStateOf(true) }

    var selectedCategoryIcon by remember { mutableIntStateOf(-1) }

    val categories by homeViewModel.categories.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        homeViewModel.fetchCategories()
    }

    if (isMarketsBottomSheetOpened) {
        BottomSheetScaffold(
            scaffoldState = rememberBottomSheetScaffoldState(),
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp / 1.75f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                MarketCardList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    markets = mockMarkets,
                    onMarketClick = { selectedMarket ->
                        coroutineScope.launch {
                            isMarketsBottomSheetOpened = false
                            marketsBottomSheetState.bottomSheetState.hide()
                            onNavigateToMarketDetails(selectedMarket)
                        }
                    }
                )
            },
            content = {
                val currentLocation = LatLng(mockUserLocation.latitude, mockUserLocation.longitude)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
                }

                var uiSettings by remember {
                    mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
                }
                var properties by remember {
                    mutableStateOf(MapProperties(mapType = MapType.NORMAL))
                }

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = properties,
                        uiSettings = uiSettings
                    ) {
                        context.getDrawable(R.drawable.ic_map_pin)?.let {
                            Marker(
                                state = MarkerState(position = currentLocation),
                                icon = BitmapDescriptorFactory.fromBitmap(it.toBitmap()),
                                title = "Localização atual"
                            )
                        }
                    }
                    when (val result = categories) {
                        is ResultState.Failure -> Toast.makeText(
                            context,
                            "Oops... Falha ao buscar categorias.",
                            Toast.LENGTH_LONG
                        ).show()

                        ResultState.Idle,
                        ResultState.Loading -> CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(24.dp)
                        )

                        is ResultState.Success -> {
                            CategoryFilterChipList(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .align(Alignment.TopStart),
                                categories = result.data,
                                onSelectedCategoryChange = { selectedCategory ->
                                    selectedCategory.icon?.let {
                                        selectedCategoryIcon = it
                                        coroutineScope.launch {
                                            isMarketsBottomSheetOpened = true
                                            marketsBottomSheetState.bottomSheetState.partialExpand()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(homeViewModel = HomeViewModel(), onNavigateToMarketDetails = {})
}