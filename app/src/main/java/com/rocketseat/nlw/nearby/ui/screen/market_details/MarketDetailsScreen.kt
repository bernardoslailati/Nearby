package com.rocketseat.nlw.nearby.ui.screen.market_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.mock.mockMarket
import com.rocketseat.nlw.nearby.data.model.mock.mockRules
import com.rocketseat.nlw.nearby.ui.component.button.NearbyButton
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsInfos
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsRules
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsUsesCoupon
import com.rocketseat.nlw.nearby.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    market: Market,
    viewModel: MarketDetailsViewModel,
    uiState: MarketDetailsUiState,
    onScanQRCode: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    var isShowValidCoupon by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchRules(marketId = market.id)
    }

    LaunchedEffect(uiState.coupon) {
        if (uiState.coupon?.isEmpty() == true) {
            Toast.makeText(
                context,
                "Nenhum cupom válido encontrado para esse local.",
                Toast.LENGTH_LONG
            ).show()
            viewModel.resetCoupon()
        }
        else {
            isShowValidCoupon = true
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentScale = ContentScale.Crop,
            model = market.cover,
            contentDescription = "Imagem de Local"
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .fillMaxHeight(0.75f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        Text(text = market.name, style = Typography.headlineLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = market.description,
                            style = Typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    MarketDetailsInfos(market = market)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                    if (uiState.rules.isNotEmpty())
                        MarketDetailsRules(rules = uiState.rules)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                    if (!uiState.coupon.isNullOrEmpty())
                        MarketDetailsUsesCoupon(coupons = listOf(uiState.coupon))
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = if (uiState.coupon.isNullOrEmpty()) "Ler QR Code" else "Voltar",
                    onClick = {
                        if (uiState.coupon.isNullOrEmpty()) {
                            onScanQRCode()
                            // viewModel.fetchCoupon(marketId = market.id)
                        } else {
                            onNavigateBack()
                            viewModel.resetCoupon()
                        }
                    }
                )
            }
        }

        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = {
                onNavigateBack()
                viewModel.resetCoupon()
            }
        )

        if (uiState.coupon?.isNotEmpty() == true && isShowValidCoupon) {
            BasicAlertDialog(
                modifier = Modifier.wrapContentSize().align(Alignment.Center),
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                onDismissRequest = {
                    isShowValidCoupon = false
                }
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            text = "Cupom válido resgatado!",
                            style = Typography.labelLarge
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            text = "\uD83C\uDF89 ${uiState.coupon} \uD83C\uDF89",
                            style = Typography.headlineLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LocationDetailsScreenPreview() {
    MarketDetailsScreen(
        market = mockMarket,
        viewModel = MarketDetailsViewModel(),
        uiState = MarketDetailsUiState(),
        onScanQRCode = {},
        onNavigateBack = {}
    )
}