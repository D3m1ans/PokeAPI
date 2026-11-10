package com.n1cks.core.data.di

import android.content.Context
import com.n1cks.core.data.local.dao.PokemonDao
import com.n1cks.core.data.local.db.PokemonDataBase
import com.n1cks.core.data.local.db.createPokemonDataBase
import com.n1cks.core.data.remote.api.PokemonApi
import com.n1cks.core.data.remote.api.createPokemonApi
import com.n1cks.core.data.repository.PokemonRepositoryImpl
import com.n1cks.core.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi = createPokemonApi()

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): PokemonDataBase = createPokemonDataBase(ctx)

    @Provides
    @Singleton
    fun provideDao(db: PokemonDataBase): PokemonDao = db.pokemonDao()

    @Provides
    @Singleton
    fun provideRepository(api: PokemonApi, db: PokemonDataBase): PokemonRepository =
        PokemonRepositoryImpl(api, db)
}