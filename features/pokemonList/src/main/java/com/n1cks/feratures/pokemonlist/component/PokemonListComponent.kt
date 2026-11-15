package com.n1cks.feratures.pokemonlist.component

import androidx.paging.PagingData
import com.arkivanov.decompose.value.Value
import com.n1cks.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonListComponent {
    val pagingData: Flow<PagingData<Pokemon>>
    val favoriteIds: Flow<Set<Int>>
    val searchQuery: Value<String>
    fun toggleFavorite(id: Int)
    fun onPokemonClick(id: Int)
    fun onSearchQueryChange(query: String)
}