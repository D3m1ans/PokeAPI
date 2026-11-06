package com.n1cks.core.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val abilities: List<String>
)
