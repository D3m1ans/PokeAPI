package com.n1cks.core.domain.usecase

import com.n1cks.core.domain.repository.PokemonRepository

class GetPokemonDetailsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) = repository.getPokemonDetails(id)
}