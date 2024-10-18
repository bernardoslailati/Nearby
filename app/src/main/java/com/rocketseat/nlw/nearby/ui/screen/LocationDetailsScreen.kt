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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.rocketseat.nlw.nearby.ui.component.button.NearbyButton
import com.rocketseat.nlw.nearby.ui.component.location_details.NearbyLocationDetailsInfos
import com.rocketseat.nlw.nearby.ui.component.location_details.NearbyLocationDetailsRegulations
import com.rocketseat.nlw.nearby.ui.component.location_details.NearbyLocationDetailsUsesCoupon
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun LocationDetailsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
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
                    .wrapContentSize()
                    .padding(36.dp)
            ) {
                Column {
                    Text(text = "RocketBurger", style = Typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Na compra de um combo SuperRocket, leve outro combo de sua escolha de graça",
                        style = Typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
                NearbyLocationDetailsInfos()
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp))
                NearbyLocationDetailsRegulations()
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp))
                NearbyLocationDetailsUsesCoupon()
            }

            NearbyButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                text = "Ler QR Code"
            ) { }
        }

        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left
        ) { }
    }
}

@Preview
@Composable
private fun LocationDetailsScreenPreview() {
    LocationDetailsScreen()
}