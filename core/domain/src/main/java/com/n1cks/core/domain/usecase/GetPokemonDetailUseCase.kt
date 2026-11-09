package com.n1cks.core.domain.usecase

import com.n1cks.core.domain.repository.PokemonRepository

class GetPokemonDetailUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) = repository.getPokemonDetail(id)
}