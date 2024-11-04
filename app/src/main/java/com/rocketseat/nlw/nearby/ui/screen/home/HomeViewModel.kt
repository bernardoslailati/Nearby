package com.rocketseat.nlw.nearby.ui.screen.home

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.rocketseat.nlw.nearby.common.network.NearbyRemoteDataSource
import com.rocketseat.nlw.nearby.data.model.Category
import com.rocketseat.nlw.nearby.data.model.Market
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    @Stable
    val categories: List<Category> = emptyList(),
    @Stable
    val markets: List<Market> = emptyList(),
    @Stable
    val marketsLatLong: List<LatLng> = emptyList()
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun fetchCategories() {
        viewModelScope.launch {
            _uiState.update {
                NearbyRemoteDataSource.getCategories().fold(
                    onSuccess = { categories ->
                        it.copy(
                            categories = categories
                        )
                    },
                    onFailure = { _ ->
                        it.copy(
                            categories = emptyList()
                        )
                    }
                )
            }
        }
    }

    fun fetchMarkets(category: Category) {
        viewModelScope.launch {
            _uiState.update {
                NearbyRemoteDataSource.getMarkets(categoryId = category.id).fold(
                    onSuccess = { markets ->
                        it.copy(
                            markets = markets,
                            marketsLatLong = markets.map { market ->
                                LatLng(market.latitude, market.longitude)
                            }
                        )
                    },
                    onFailure = { _ ->
                        it.copy(
                            markets = emptyList(),
                            marketsLatLong = emptyList()
                        )
                    }
                )
            }
        }
    }

}