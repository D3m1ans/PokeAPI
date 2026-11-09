package com.n1cks.features.pokemondetail.state

import com.n1cks.core.domain.model.PokemonDetail

sealed class PokemonDetailState {
    object Loading : PokemonDetailState()
    data class Success(val detail: PokemonDetail) : PokemonDetailState()
    data class Error(val message: String) : PokemonDetailState()
}