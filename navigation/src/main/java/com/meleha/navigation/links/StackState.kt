package com.meleha.navigation.links

import com.meleha.navigation.Route

data class StackState(
    val routes: List<Route>
) {
    fun withNewRoute(route: Route): StackState = copy(
        routes = routes + route
    )
}