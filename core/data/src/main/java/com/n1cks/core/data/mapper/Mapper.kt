package com.n1cks.core.data.mapper

import com.n1cks.core.data.local.entity.PokemonEntity
import com.n1cks.core.data.remote.dto.PokemonDetailDto
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.model.PokemonDetail

fun PokemonEntity.toDomain() = Pokemon(
    id = id,
    name = name,
    imageUrl = imageUrl,
    types = emptyList()
)

fun PokemonDetailDto.toDomain() = PokemonDetail(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
    height = height,
    weight = weight,
    types = types.map { it.type.name },
    abilities = abilities.map { it.ability.name }
)