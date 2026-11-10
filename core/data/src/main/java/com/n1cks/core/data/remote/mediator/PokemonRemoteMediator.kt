package com.n1cks.core.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room3.withWriteTransaction
import com.n1cks.core.data.local.db.PokemonDataBase
import com.n1cks.core.data.local.entity.PokemonEntity
import com.n1cks.core.data.remote.api.PokemonApi

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokemonApi,
    private val db: PokemonDataBase
): RemoteMediator<Int, PokemonEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val pageSize = state.config.pageSize
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(true)
                    lastItem.id + pageSize
                }
            }

            val response = api.getPokemonList(offset = offset, limit = pageSize)
            val entities = response.results.map { dto ->
                val id = dto.url.trimEnd('/').substringAfterLast('/').toInt()
                PokemonEntity(
                    id = id,
                    name = dto.name.replaceFirstChar { it.uppercase() },
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
                )
            }
            val end = response.next == null

            db.withWriteTransaction {
                if (loadType == LoadType.REFRESH) db.pokemonDao().clearAll()
                db.pokemonDao().insertAll(entities)
            }
            MediatorResult.Success(endOfPaginationReached = end)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}