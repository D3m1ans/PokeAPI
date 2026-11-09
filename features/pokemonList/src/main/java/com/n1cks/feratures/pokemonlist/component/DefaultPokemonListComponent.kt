package com.n1cks.feratures.pokemonlist.component

import androidx.paging.PagingData
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.usecase.GetFavoritesIdsUseCase
import com.n1cks.core.domain.usecase.GetPokemonListUseCase
import com.n1cks.core.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DefaultPokemonListComponent(
    componentContext: ComponentContext,
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getFavoritesIdsUseCase: GetFavoritesIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val onOpenDetails: (Int) -> Unit
): PokemonListComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        lifecycle.doOnDestroy { scope.cancel() }
    }

    override val pagingData: Flow<PagingData<Pokemon>> = getPokemonListUseCase()
    override val favoriteIds: Flow<Set<Int>> = getFavoritesIdsUseCase()

    override fun toggleFavorite(id: Int) {
        scope.launch { toggleFavoriteUseCase(id) }
    }

    override fun onPokemonClick(id: Int) {
        onOpenDetails(id)
    }

}