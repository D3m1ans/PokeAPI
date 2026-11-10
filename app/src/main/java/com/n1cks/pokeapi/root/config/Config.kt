package com.n1cks.pokeapi.root.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface Config {
    @Serializable data object List: Config
    @Serializable data class Detail(val id: Int): Config
}