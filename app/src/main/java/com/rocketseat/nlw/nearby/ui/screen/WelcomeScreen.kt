package com.rocketseat.nlw.nearby.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.ui.component.button.NearbyButton
import com.rocketseat.nlw.nearby.ui.component.welcome.HowItWorksTip
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 40.dp, vertical = 48.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo_icon),
                contentDescription = "Nearby App Logo"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Boas vindas ao Nearby", style = Typography.headlineLarge)
            Text(
                text = "Tenha cupons de vantagem para usar em seus estabelecimentos favoritos.",
                style = Typography.bodyLarge
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(text = "Veja como funciona:", style = Typography.bodyLarge)
            HowItWorksTip(
                modifier = Modifier.fillMaxWidth(),
                title = "Encontre estabelecimentos",
                subtitle = "Veja locais perto de você que são parceiros Nearby",
                iconRes = R.drawable.ic_map_pin
            )
            HowItWorksTip(
                modifier = Modifier.fillMaxWidth(),
                title = "Ative o cupom com QR Code",
                subtitle = "Escaneie o código no estabelecimento para usar o benefício",
                iconRes = R.drawable.ic_qrcode
            )
            HowItWorksTip(
                modifier = Modifier.fillMaxWidth(),
                title = "Garanta vantagens perto de você",
                subtitle = "Ative cupons onde estiver, em diferentes tipos de estabelecimento ",
                iconRes = R.drawable.ic_ticket
            )
        }
        NearbyButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Começar",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(modifier = Modifier)
}