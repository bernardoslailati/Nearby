package com.rocketseat.nlw.nearby.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.CameraUpdateFactory
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
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.mock.mockUserLocation
import com.rocketseat.nlw.nearby.ui.component.category.CategoryFilterChipList
import com.rocketseat.nlw.nearby.ui.component.market.MarketCardList
import com.rocketseat.nlw.nearby.ui.screen.home.utils.dpToPx
import com.rocketseat.nlw.nearby.ui.screen.home.utils.findNortheastPoint
import com.rocketseat.nlw.nearby.ui.screen.home.utils.findSouthwestPoint
import com.rocketseat.nlw.nearby.ui.theme.Gray100
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    viewModel: HomeViewModel,
    onNavigateToMarketDetails: (Market) -> Unit
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val density = LocalDensity.current

    val coroutineScope = rememberCoroutineScope()

    val marketsBottomSheetState =
        rememberBottomSheetScaffoldState(bottomSheetState = rememberModalBottomSheetState())
    var isMarketsBottomSheetOpened by remember { mutableStateOf(true) }

    var selectedCategoryIcon by remember { mutableIntStateOf(-1) }

    LaunchedEffect(true) {
        viewModel.fetchCategories()
    }

    if (isMarketsBottomSheetOpened) {
        BottomSheetScaffold(
            scaffoldState = rememberBottomSheetScaffoldState(),
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp / 1.75f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                if (uiState.markets.isNotEmpty())
                    MarketCardList(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        markets = uiState.markets,
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
                    position = CameraPosition.fromLatLngZoom(currentLocation, 13f)
                }
                val uiSettings by remember {
                    mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
                }
                val properties by remember {
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
                        context.getDrawable(R.drawable.img_user_location)?.let { icUserLocation ->
                            Marker(
                                state = MarkerState(position = currentLocation),
                                icon = BitmapDescriptorFactory.fromBitmap(
                                    icUserLocation.toBitmap(
                                        width = density.dpToPx(dp = 72.dp),
                                        height = density.dpToPx(dp = 72.dp)
                                    )
                                ),
                                title = "Localização atual",
                            )
                        }

                        if (uiState.markets.isNotEmpty()) {
                            context.getDrawable(R.drawable.img_pin)?.let { imgPin ->
                                uiState.marketsLatLong.toImmutableList().forEach { latLong ->
                                    Marker(
                                        state = MarkerState(position = latLong),
                                        icon = BitmapDescriptorFactory.fromBitmap(
                                            imgPin.toBitmap(
                                                width = density.dpToPx(dp = 36.dp),
                                                height = density.dpToPx(dp = 36.dp),
                                            )
                                        ),
                                        title = uiState.markets[
                                            uiState.marketsLatLong.indexOf(latLong)
                                        ].name,
                                    )
                                }.also {
                                    coroutineScope.launch {
                                        val allMarks = uiState.marketsLatLong + mockUserLocation
                                        val southwestPoint = findSouthwestPoint(allMarks)
                                        val northeastPoint = findNortheastPoint(allMarks)

                                        val centerZoomLatitude =
                                            (southwestPoint.latitude + northeastPoint.latitude) / 2
                                        val centerZoomLongitude =
                                            (southwestPoint.longitude + northeastPoint.longitude) / 2

                                        val cameraUpdate = CameraUpdateFactory.newCameraPosition(
                                            CameraPosition(
                                                LatLng(
                                                    centerZoomLatitude,
                                                    centerZoomLongitude
                                                ),
                                                13f,
                                                0f,
                                                0f
                                            )
                                        )
                                        delay(200)
                                        cameraPositionState.animate(cameraUpdate, durationMs = 500)
                                    }
                                }
                            }
                        }
                    }


                    if (uiState.categories.isEmpty())
                        IconButton(
                            modifier = Modifier.align(Alignment.TopCenter),
                            onClick = {
                                viewModel.fetchCategories()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                        }
                    else {
                        CategoryFilterChipList(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .align(Alignment.TopStart),
                            categories = uiState.categories,
                            onSelectedCategoryChange = { selectedCategory ->
                                viewModel.fetchMarkets(category = selectedCategory)

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
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(viewModel = HomeViewModel(), uiState = HomeUiState(), onNavigateToMarketDetails = {})
}