package com.n1cks.features.pokemondetail.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.n1cks.features.pokemondetail.component.PokemonDetailComponent
import com.n1cks.features.pokemondetail.state.PokemonDetailState
import com.n1cks.features.pokemondetail.ui.content.PokemonDetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    component: PokemonDetailComponent
) {
    val state by component.state.subscribeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Pokemon")},
                navigationIcon = {
                    IconButton(onClick = component::onBack) {
                        Icon(Icons.Default.ArrowBackIosNew, null)
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val s = state) {
            is PokemonDetailState.Loading -> {
                Box(Modifier.padding(paddingValues).fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            is PokemonDetailState.Error -> {
                Box(Modifier.padding(paddingValues).fillMaxSize()) {
                    Text(s.message, Modifier.align(Alignment.Center))
                }
            }
            is PokemonDetailState.Success -> {
                PokemonDetailContent(pokemon = s.detail)
            }
        }
    }
}