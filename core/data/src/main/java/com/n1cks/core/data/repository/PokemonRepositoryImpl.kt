package com.n1cks.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.n1cks.core.data.local.db.PokemonDataBase
import com.n1cks.core.data.mapper.toDomain
import com.n1cks.core.data.remote.api.PokemonApi
import com.n1cks.core.data.remote.mediator.PokemonRemoteMediator
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.core.domain.model.PokemonDetail
import com.n1cks.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl(
    private val api: PokemonApi,
    private val db: PokemonDataBase,
): PokemonRepository {

    private val dao = db.pokemonDao()

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = PokemonRemoteMediator(api, db),
            pagingSourceFactory = { dao.pagingSource() }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override suspend fun getPokemonDetails(id: Int): PokemonDetail?  =
        try { api.getPokemon(id).toDomain() } catch (_: Exception) { null }


    override suspend fun toggleFavorite(id: Int) = dao.toggleFavorite(id)

    override fun getFavoritesIds(): Flow<Set<Int>> = dao.favoriteIds()

}