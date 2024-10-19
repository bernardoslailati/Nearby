package com.rocketseat.nlw.nearby.ui.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.ui.theme.GreenBase
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun NearbyButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes iconRes: Int? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .heightIn(min = 56.dp),
        shape = RoundedCornerShape(12.dp),
        contentPadding = if (iconRes != null && text == null) PaddingValues(0.dp) else ButtonDefaults.ContentPadding,
        colors = ButtonDefaults.buttonColors(containerColor = GreenBase),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            iconRes?.let {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Ícone do Botão"
                )
            }
            text?.let {
                Text(text = text.uppercase(), style = Typography.labelLarge)
            }
        }
    }
}

@Preview
@Composable
private fun NearbyButtonWithIconPreview() {
    NearbyButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Ler QRCode",
        iconRes = R.drawable.ic_scan,
        onClick = {}
    )
}

@Preview
@Composable
private fun NearbyButtonWithoutIconPreview() {
    NearbyButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Começar",
        onClick = {}
    )
}

@Preview
@Composable
private fun NearbyIconButtonPreview() {
    NearbyButton(
        iconRes = R.drawable.ic_arrow_left,
        onClick = {}
    )
}