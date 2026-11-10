package com.n1cks.pokeapi.root.child

import com.n1cks.features.pokemondetail.component.PokemonDetailComponent
import com.n1cks.feratures.pokemonlist.component.PokemonListComponent

sealed class Child {
    data class PokemonListChild(val component: PokemonListComponent): Child()
    data class PokemonDetailChild(val component: PokemonDetailComponent): Child()
}