package com.n1cks.pokeapi.root.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.n1cks.features.pokemondetail.ui.screen.PokemonDetailScreen
import com.n1cks.feratures.pokemonlist.ui.screen.PokemonListScreen
import com.n1cks.pokeapi.root.child.Child
import com.n1cks.pokeapi.root.component.RootComponent

@Composable
fun RootContent(component: RootComponent) {
    Children(
        stack = component.stack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is Child.PokemonListChild -> PokemonListScreen(child.component)
            is Child.PokemonDetailChild -> PokemonDetailScreen(child.component)
        }
    }
}