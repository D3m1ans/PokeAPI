package com.n1cks.feratures.pokemonlist.component

import androidx.paging.PagingData
import com.n1cks.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonListComponent {
    val pagingData: Flow<PagingData<Pokemon>>
    val favoriteIds: Flow<Set<Int>>
    fun toggleFavorite(id: Int)
    fun onPokemonClick(id: Int)
}