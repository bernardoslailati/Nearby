package com.rocketseat.nlw.nearby.data.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Rule(
    val id: String,
    val description: String,
    val marketId: String
)