package com.meleha.navigation

import androidx.compose.runtime.Stable

@Stable
interface Router {

    fun launch(route: Route)

    fun pop(response: Any? = null)

    fun restart(route: Route) = restart(listOf(route))

    fun restart(routeRouters: List<Route>, initialIndex: Int = 1)

    fun switchStack(index: Int)
}