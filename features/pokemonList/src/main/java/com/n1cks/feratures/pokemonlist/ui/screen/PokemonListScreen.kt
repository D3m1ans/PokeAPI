package com.n1cks.feratures.pokemonlist.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.feratures.pokemonlist.component.PokemonListComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    component: PokemonListComponent
) {
    val pagingItems = component.pagingData.collectAsLazyPagingItems()
    val favoriteIds by component.favoriteIds.collectAsState(initial = emptySet())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PokeMaster") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = pagingItems.itemCount) { index ->
                val pokemon = pagingItems[index]
                if (pokemon != null) {
                    PokemonCard(
                        pokemon = pokemon,
                        isFavorite = pokemon.id in favoriteIds,
                        onClick = { component.onPokemonClick(pokemon.id) },
                        onToggleFavorite = { component.toggleFavorite(pokemon.id) }
                    )
                }
            }
        }
    }

}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageUrl)
                    .crossfade(true)
                    .size(128)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    pokemon.name,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = pokemon.region(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

private fun Pokemon.region(): String = when (id) {
    in 1..151 -> "Kanto"
    in 152..251 -> "Johto"
    in 252..386 -> "Hoenn"
    in 387..493 -> "Sinnoh"
    in 494..649 -> "Unova"
    in 650..721 -> "Kalos"
    in 722..809 -> "Alola"
    in 810..905 -> "Galar"
    else -> "Paldea"
}