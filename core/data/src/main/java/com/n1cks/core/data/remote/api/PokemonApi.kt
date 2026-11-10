package com.n1cks.core.data.remote.api

import com.n1cks.core.data.remote.dto.PokemonDetailDto
import com.n1cks.core.data.remote.dto.PokemonListResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val PokemonJson = Json {ignoreUnknownKeys = true}

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDetailDto
}

fun createPokemonApi(): PokemonApi =
    Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(PokemonJson.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(PokemonApi::class.java)