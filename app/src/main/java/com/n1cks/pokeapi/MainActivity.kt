package com.n1cks.pokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.retainedComponent
import com.n1cks.core.domain.usecase.GetFavoritesIdsUseCase
import com.n1cks.core.domain.usecase.GetPokemonDetailUseCase
import com.n1cks.core.domain.usecase.GetPokemonListUseCase
import com.n1cks.core.domain.usecase.ToggleFavoriteUseCase
import com.n1cks.pokeapi.root.component.DefaultRootComponent
import com.n1cks.pokeapi.root.ui.RootContent
import com.n1cks.pokeapi.ui.theme.PokeapiTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   @Inject lateinit var getPokemonList: GetPokemonListUseCase
    @Inject lateinit var getFavoritesIds: GetFavoritesIdsUseCase
    @Inject lateinit var toggleFavorite: ToggleFavoriteUseCase
    @Inject lateinit var getPokemonDetail: GetPokemonDetailUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = retainedComponent { context ->
            DefaultRootComponent(
                componentContext = context,
                getPokemonList = getPokemonList,
                getFavoritesIds = getFavoritesIds,
                toggleFavorite = toggleFavorite,
                getPokemonDetail = getPokemonDetail,
            )
        }

        setContent {
            PokeapiTheme {
                RootContent(root)
            }
        }
    }
}

