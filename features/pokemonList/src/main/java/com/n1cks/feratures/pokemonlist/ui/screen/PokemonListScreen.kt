package com.n1cks.feratures.pokemonlist.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.n1cks.core.domain.model.Pokemon
import com.n1cks.feratures.pokemonlist.component.PokemonListComponent
import java.nio.file.WatchEvent

@Composable
fun PokemonListScreen(
    component: PokemonListComponent
) {
    val pagingItems = component.pagingData.collectAsLazyPagingItems()
    val favoriteIds by component.favoriteIds.collectAsState(initial = emptySet())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
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
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(pokemon.name, style = MaterialTheme.typography.titleSmall)
                Text(pokemon.types.joinToString(), style = MaterialTheme.typography.bodyMedium)
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