package com.rocketseat.nlw.nearby.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import com.rocketseat.nlw.nearby.ui.component.location.NearbyLocation
import com.rocketseat.nlw.nearby.ui.component.location.NearbyLocationCard
import com.rocketseat.nlw.nearby.ui.component.location_filter.NearbyFilterList
import com.rocketseat.nlw.nearby.ui.theme.GreenBase
import com.rocketseat.nlw.nearby.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var isSheetOpened by remember { mutableStateOf(true) }

    var iconButton by remember { mutableIntStateOf(-1) }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg_map),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        NearbyFilterList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .align(Alignment.TopStart)
        ) {
            iconButton = it.icon
        }

        if (iconButton > 0)
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                containerColor = GreenBase,
                contentColor = Color.White,
                onClick = {
                    coroutineScope.launch {
                        isSheetOpened = true
                        sheetState.partialExpand()
                    }
                }
            ) {
                Icon(painter = painterResource(iconButton), contentDescription = null)
            }
    }

    if (isSheetOpened)
        ModalBottomSheet(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            containerColor = Color.White,
            tonalElevation = 0.dp,
            onDismissRequest = { isSheetOpened = false }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(text = "Explore locais perto de você", style = Typography.bodyLarge)
                }
                items(count = 5, key = { it }) {
                    NearbyLocationCard(
                        nearbyLocation = NearbyLocation(
                            name = "RocketBurger",
                            description = "Na compra de um combo SuperRocket, leve outro combo de sua escolha de graça",
                            imageUrl = "",
                            coupons = 0
                        ),
                        onClick = {
                            coroutineScope.launch {
                                isSheetOpened = false
                                sheetState.hide()
                            }
                        }
                    )
                }
            }
        }

}

@Preview
@Composable
private fun MapScreenPreview() {
    MapScreen()
}