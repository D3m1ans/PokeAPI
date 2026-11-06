package com.n1cks.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSummaryDto(
    val name: String,
    val url: String
)
