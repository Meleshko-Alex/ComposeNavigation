package com.meleha.navigation.internal

import com.meleha.navigation.ScreenResponseReceiver
import kotlinx.coroutines.flow.Flow

sealed class NavigationEvent {
    data class Removed(val route: RouteRecord?) : NavigationEvent()
}

interface InternalNavigationState {
    val currentUuid: String
    val screenResponseReceiver: ScreenResponseReceiver
    fun listen(): Flow<NavigationEvent>
}