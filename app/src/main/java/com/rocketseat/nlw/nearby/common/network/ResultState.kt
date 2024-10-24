package com.rocketseat.nlw.nearby.common.network

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Failure(val exception: Throwable) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
    data object Idle : ResultState<Nothing>()
}