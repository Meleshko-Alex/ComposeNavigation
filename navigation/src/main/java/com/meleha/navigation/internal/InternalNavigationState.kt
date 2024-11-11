package com.meleha.navigation.internal

import com.meleha.navigation.Route
import kotlinx.coroutines.flow.Flow

sealed class NavigationEvent {
    data class Removed(val route: Route) : NavigationEvent()
}

interface InternalNavigationState {
    fun listen(): Flow<NavigationEvent>
}