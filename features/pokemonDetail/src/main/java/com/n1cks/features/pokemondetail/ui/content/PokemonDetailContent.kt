package com.n1cks.features.pokemondetail.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.n1cks.core.domain.model.PokemonDetail

@Composable
fun PokemonDetailContent(
    pokemon: PokemonDetail
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(pokemon.name, style = MaterialTheme.typography.titleMedium)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Height: ${pokemon.height} dm", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.width(16.dp))
            Text("Weight: ${pokemon.weight} hg", style = MaterialTheme.typography.bodyLarge)
        }

        Text("Types: ${pokemon.types.joinToString()}", style = MaterialTheme.typography.bodyLarge)
        Text(
            "Abilities: ${pokemon.abilities.joinToString()}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}