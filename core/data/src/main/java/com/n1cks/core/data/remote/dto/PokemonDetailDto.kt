package com.n1cks.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerialName("abilities") val abilities: List<AbilityDto>,
    @SerialName("types") val types: List<TypeSlotDto>
) {
    @Serializable data class AbilityDto(val ability: NamedApiResource)
    @Serializable data class TypeSlotDto(val type: NamedApiResource)
    @Serializable data class NamedApiResource(val name: String)
}
