package com.n1cks.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val result: List<PokemonSummaryDto>
)
