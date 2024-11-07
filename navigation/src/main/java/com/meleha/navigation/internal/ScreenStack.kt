package com.meleha.navigation.internal

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.meleha.navigation.NavigationState
import com.meleha.navigation.Route
import com.meleha.navigation.Router

class ScreenStack(
    private val routes: SnapshotStateList<Route>,
    ) : NavigationState, Router {

    override val isRoot: Boolean
        get() = routes.size == 1

    override val currentRoute: Route
        get() = routes.last()

    override fun launch(route: Route) {
        routes.add(route)
    }

    override fun pop() {
        routes.removeLastOrNull()
    }

    override fun restart(route: Route) {
        routes.apply {
            clear()
            add(route)
        }
    }
}
