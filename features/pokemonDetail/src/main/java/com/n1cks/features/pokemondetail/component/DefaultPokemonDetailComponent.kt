package com.n1cks.features.pokemondetail.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.n1cks.core.domain.usecase.GetPokemonDetailUseCase
import com.n1cks.features.pokemondetail.state.PokemonDetailState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DefaultPokemonDetailComponent(
    componentContext: ComponentContext,
    private val pokemonId: Int,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val onBackClick: () -> Unit
): PokemonDetailComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _state = MutableValue<PokemonDetailState>(PokemonDetailState.Loading)
    override val state: Value<PokemonDetailState> = _state

    init {
        lifecycle.doOnDestroy { scope.cancel() }
        loadDetail()
    }


    private fun loadDetail() {
        scope.launch {
            try {
                val detail = getPokemonDetailUseCase(pokemonId)
                if (detail != null) {
                    _state.value = PokemonDetailState.Success(detail)
                } else {
                    _state.value = PokemonDetailState.Error("Pokemon not found")
                }
            } catch (e: Exception) {
                _state.value = PokemonDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }

    override fun onBack() {
        onBackClick()
    }

}