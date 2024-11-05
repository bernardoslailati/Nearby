package com.rocketseat.nlw.nearby.ui.screen.market_details

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocketseat.nlw.nearby.common.network.NearbyRemoteDataSource
import com.rocketseat.nlw.nearby.data.model.Rule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MarketDetailsUiState(
    @Stable
    val rules: List<Rule> = emptyList(),
    val coupon: String? = null,
)

class MarketDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MarketDetailsUiState())
    val uiState: StateFlow<MarketDetailsUiState> = _uiState.asStateFlow()

    fun fetchRules(marketId: String) {
        viewModelScope.launch {
            NearbyRemoteDataSource.getMarketDetails(marketId = marketId)
                .onSuccess {
                    _uiState.update { uiState ->
                        uiState.copy(
                            rules = it.rules
                        )
                    }
                }
                .onFailure {
                    _uiState.update { uiState ->
                        uiState.copy(
                            coupon = null
                        )
                    }
                }
        }
    }

    fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            NearbyRemoteDataSource.patchCoupons(marketId = qrCodeContent)
                .onSuccess {
                    Log.d("OPAOPA", "Success $it")
                    _uiState.update { uiState ->
                        uiState.copy(
                            coupon = it.coupon
                        )
                    }
                }
                .onFailure {
                    _uiState.update { uiState ->
                        uiState.copy(
                            coupon = ""
                        )
                    }
                }
        }
    }

    fun resetCoupon() {
        _uiState.update { uiState ->
            uiState.copy(
                coupon = null
            )
        }
    }
}