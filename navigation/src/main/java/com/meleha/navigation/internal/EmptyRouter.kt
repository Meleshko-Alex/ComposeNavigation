package com.meleha.navigation.internal

import com.meleha.navigation.Route
import com.meleha.navigation.Router

internal object EmptyRouter : Router {
    override fun launch(route: Route) = Unit

    override fun pop(response: Any?) = Unit

    override fun restart(routeRouters: List<Route>, initialIndex: Int) = Unit

    override fun switchStack(index: Int) = Unit
}