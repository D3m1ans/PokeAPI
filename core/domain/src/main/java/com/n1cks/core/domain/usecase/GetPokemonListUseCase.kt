package com.n1cks.core.domain.usecase

import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<List<Pokemon>> = repository.getPokemonList()
}