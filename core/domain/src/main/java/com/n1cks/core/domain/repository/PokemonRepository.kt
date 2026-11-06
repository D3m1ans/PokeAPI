package com.n1cks.core.domain.repository

import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<List<Pokemon>>
    suspend fun getPokemonDetails(id: Int): PokemonDetails?
    suspend fun toggleFavorite(id: Int)
    fun getFavoritesIds(): Flow<Set<Int>>
}