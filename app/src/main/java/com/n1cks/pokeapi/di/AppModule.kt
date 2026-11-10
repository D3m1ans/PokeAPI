package com.n1cks.pokeapi.di

import com.n1cks.core.domain.repository.PokemonRepository
import com.n1cks.core.domain.usecase.GetFavoritesIdsUseCase
import com.n1cks.core.domain.usecase.GetPokemonDetailUseCase
import com.n1cks.core.domain.usecase.GetPokemonListUseCase
import com.n1cks.core.domain.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(repository: PokemonRepository): GetPokemonListUseCase =
        GetPokemonListUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavoritesIdsUseCase(repository: PokemonRepository): GetFavoritesIdsUseCase =
        GetFavoritesIdsUseCase(repository)

    @Provides
    @Singleton
    fun provideToggleFavoriteUseCase(repository: PokemonRepository): ToggleFavoriteUseCase =
        ToggleFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPokemonDetailsUseCase(repository: PokemonRepository): GetPokemonDetailUseCase =
        GetPokemonDetailUseCase(repository)
}