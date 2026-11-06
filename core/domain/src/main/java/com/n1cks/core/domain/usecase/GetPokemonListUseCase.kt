package com.n1cks.core.domain.usecase

import androidx.paging.PagingData
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> = repository.getPokemonList()
}