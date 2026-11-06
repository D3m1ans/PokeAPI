package com.n1cks.core.domain.repository

import androidx.paging.PagingData
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetails(id: Int): PokemonDetail?
    suspend fun toggleFavorite(id: Int)
    fun getFavoritesIds(): Flow<Set<Int>>
}