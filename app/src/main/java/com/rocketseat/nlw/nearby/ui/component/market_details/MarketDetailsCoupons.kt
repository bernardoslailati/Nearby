package com.rocketseat.nlw.nearby.ui.component.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.ui.theme.Gray400
import com.rocketseat.nlw.nearby.ui.theme.GreenBase
import com.rocketseat.nlw.nearby.ui.theme.GreenExtraLight
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun NearbyLocationDetailsUsesCoupon(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Utilize esse cupom", style = Typography.headlineSmall, color = Gray400)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(
                    GreenExtraLight
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_ticket),
                tint = GreenBase,
                contentDescription = "Ícone Cupons"
            )
            Text(
                text = "FM4345T6",
                style = Typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
private fun NearbyLocationDetailsUsesCouponPreview() {
    NearbyLocationDetailsUsesCoupon()
}