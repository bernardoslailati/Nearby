package com.rocketseat.nlw.nearby.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocketseat.nlw.nearby.common.network.NetworkManager
import com.rocketseat.nlw.nearby.common.network.ResultState
import com.rocketseat.nlw.nearby.data.model.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _categories = MutableStateFlow<ResultState<List<Category>>>(ResultState.Idle)
    val categories: StateFlow<ResultState<List<Category>>> = _categories.asStateFlow()

    fun fetchCategories() {
        viewModelScope.launch {
            _categories.emit(ResultState.Loading)
            delay(2_000)
            _categories.emit(
                NetworkManager.getCategories().fold(
                    onSuccess = { categories -> ResultState.Success(categories) },
                    onFailure = { exception -> ResultState.Failure(exception) }
                )
            )
        }
    }

}