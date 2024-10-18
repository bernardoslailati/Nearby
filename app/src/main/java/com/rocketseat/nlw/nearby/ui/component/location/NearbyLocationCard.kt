package com.rocketseat.nlw.nearby.ui.component.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.ui.theme.Gray100
import com.rocketseat.nlw.nearby.ui.theme.Gray200
import com.rocketseat.nlw.nearby.ui.theme.Gray400
import com.rocketseat.nlw.nearby.ui.theme.Gray500
import com.rocketseat.nlw.nearby.ui.theme.RedBase
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun NearbyLocationCard(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Gray100)
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(12.dp)
            ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray100)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth(0.35f)
                    .height(IntrinsicSize.Min)
                    .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.img_burger),
                contentDescription = "Imagem de Local Próximo"
            )
            Column {
                Text(
                    text = "RocketBurger",
                    style = Typography.headlineSmall.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Na compra de um combo SuperRocket, leve outro combo de sua escolha de graça",
                    color = Gray500,
                    style = Typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        tint = RedBase,
                        painter = painterResource(R.drawable.ic_ticket),
                        contentDescription = "Ícone de Cupom"
                    )
                    Text(
                        text = "3 cupons disponíveis",
                        style = Typography.bodyMedium.copy(fontSize = 12.sp),
                        color = Gray400
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun NearbyLocationCardPreview() {
    NearbyLocationCard()
}

@Preview
@Composable
private fun NearbyLocationCardListPreview() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = 5) {
            NearbyLocationCard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NearbyLocationCardsBottomSheetPreview() {
    Column {
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismissRequest = {}
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(count = 5) {
                    NearbyLocationCard()
                }
            }
        }
    }
}