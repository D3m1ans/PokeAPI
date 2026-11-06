package com.n1cks.core.data.local.db

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.n1cks.core.data.local.dao.PokemonDao
import com.n1cks.core.data.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDataBase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}

fun createPokemonDataBase(context: Context): PokemonDataBase =
    Room.databaseBuilder(
        context.applicationContext,
        PokemonDataBase::class.java,
        "pokemon.db"
    ).build()