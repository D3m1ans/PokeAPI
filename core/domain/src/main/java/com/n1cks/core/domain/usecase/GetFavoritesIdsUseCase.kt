package com.n1cks.core.domain.usecase

import com.n1cks.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesIdsUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke() : Flow<Set<Int>> = repository.getFavoritesIds()
}