package com.rocketseat.nlw.nearby.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.mock.mockCategories
import com.rocketseat.nlw.nearby.data.model.mock.mockMarkets
import com.rocketseat.nlw.nearby.ui.component.category.CategoryFilterChipList
import com.rocketseat.nlw.nearby.ui.component.market.MarketCardList
import com.rocketseat.nlw.nearby.ui.theme.Gray100
import com.rocketseat.nlw.nearby.ui.theme.GreenBase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onNavigateToMarketDetails: (Market) -> Unit) {

    val coroutineScope = rememberCoroutineScope()

    val marketsBottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var isMarketsBottomSheetOpened by remember { mutableStateOf(true) }

    var selectedCategoryIcon by remember { mutableIntStateOf(-1) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg_map),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        CategoryFilterChipList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .align(Alignment.TopStart),
            categories = mockCategories,
            onSelectedCategoryChange = { selectedCategory ->
                selectedCategory.icon?.let {
                    selectedCategoryIcon = it
                    coroutineScope.launch {
                        isMarketsBottomSheetOpened = true
                        marketsBottomSheetState.partialExpand()
                    }
                }
            }
        )

        if (selectedCategoryIcon > 0) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                containerColor = GreenBase,
                contentColor = Color.White,
                onClick = {
                    coroutineScope.launch {
                        isMarketsBottomSheetOpened = true
                        marketsBottomSheetState.partialExpand()
                    }
                }
            ) {
                Icon(painter = painterResource(selectedCategoryIcon), contentDescription = null)
            }
        }
    }

    if (isMarketsBottomSheetOpened) {
        ModalBottomSheet(
            sheetState = marketsBottomSheetState,
            containerColor = Gray100,
            tonalElevation = 0.dp,
            scrimColor = Color.Transparent,
            onDismissRequest = { isMarketsBottomSheetOpened = false }
        ) {
            MarketCardList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                markets = mockMarkets,
                onMarketClick = { selectedMarket ->
                    coroutineScope.launch {
                        isMarketsBottomSheetOpened = false
                        marketsBottomSheetState.hide()
                        onNavigateToMarketDetails(selectedMarket)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onNavigateToMarketDetails = {})
}