package com.meleha.navigation.internal

import com.meleha.navigation.Route
import com.meleha.navigation.Router

internal object EmptyRouter : Router {
    override fun launch(route: Route) = Unit

    override fun pop() = Unit

    override fun restart(route: Route) = Unit
}