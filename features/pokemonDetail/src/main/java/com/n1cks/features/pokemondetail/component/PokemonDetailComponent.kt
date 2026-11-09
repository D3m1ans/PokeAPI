package com.n1cks.features.pokemondetail.component

import com.arkivanov.decompose.value.Value
import com.n1cks.features.pokemondetail.state.PokemonDetailState

interface PokemonDetailComponent {
    val state: Value<PokemonDetailState>
    fun onBack()
}