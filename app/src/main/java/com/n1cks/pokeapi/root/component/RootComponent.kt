package com.n1cks.pokeapi.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.n1cks.pokeapi.root.child.Child

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>
}