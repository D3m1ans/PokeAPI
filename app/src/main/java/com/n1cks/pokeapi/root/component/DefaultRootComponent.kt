package com.n1cks.pokeapi.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.n1cks.core.domain.usecase.GetFavoritesIdsUseCase
import com.n1cks.core.domain.usecase.GetPokemonDetailUseCase
import com.n1cks.core.domain.usecase.GetPokemonListUseCase
import com.n1cks.core.domain.usecase.ToggleFavoriteUseCase
import com.n1cks.features.pokemondetail.component.DefaultPokemonDetailComponent
import com.n1cks.feratures.pokemonlist.component.DefaultPokemonListComponent
import com.n1cks.pokeapi.root.child.Child
import com.n1cks.pokeapi.root.config.Config

@OptIn(DelicateDecomposeApi::class)
class DefaultRootComponent(
    componentContext: ComponentContext,
    private val getPokemonList: GetPokemonListUseCase,
    private val getFavoritesIds: GetFavoritesIdsUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
    private val getPokemonDetail: GetPokemonDetailUseCase,
): RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.List,
        handleBackButton = true,
        serializer = Config.serializer(),
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext): Child =
        when(config) {
            is Config.List -> Child.PokemonListChild(
                DefaultPokemonListComponent(
                    componentContext = context,
                    getPokemonListUseCase = getPokemonList,
                    getFavoritesIdsUseCase = getFavoritesIds,
                    toggleFavoriteUseCase = toggleFavorite,
                    onOpenDetails = { id -> navigation.push(Config.Detail(id)) }
                )
            )
            is Config.Detail -> Child.PokemonDetailChild(
                DefaultPokemonDetailComponent(
                    componentContext = context,
                    pokemonId = config.id,
                    getPokemonDetailUseCase = getPokemonDetail,
                    onBackClick = {navigation.pop()}
                )
            )
        }
}