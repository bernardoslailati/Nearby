package com.rocketseat.nlw.nearby.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.mock.mockMarket
import com.rocketseat.nlw.nearby.ui.component.button.NearbyButton
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsInfos
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsRules
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsUsesCoupon
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    market: Market,
    coupons: List<String>,
    onScanQRCode: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.img_burger),
            contentDescription = null
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
                    MarketDetailsRules(rules = market.rules)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                    MarketDetailsUsesCoupon(coupons = coupons)
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code"
                ) { }
            }
        }

        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left
        ) {
            onScanQRCode()
        }
    }
}

@Preview
@Composable
private fun LocationDetailsScreenPreview() {
    MarketDetailsScreen(
        market = mockMarket,
        coupons = listOf("AM4345T1", "BM4345T2"),
        onScanQRCode = {}
    )
}