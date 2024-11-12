package com.meleha.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.staticCompositionLocalOf
import com.meleha.navigation.internal.EmptyRouter
import com.meleha.navigation.internal.NavigationEvent
import kotlinx.coroutines.flow.filterIsInstance

val LocalRouter = staticCompositionLocalOf<Router> { EmptyRouter }

@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
) {
    val (router, navigationState) = navigation
    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = navigationState.currentRoute) {
        Box(modifier = modifier) {
            CompositionLocalProvider(
                LocalRouter provides router
            ) {
                navigationState.currentScreen.Content()
            }
        }
    }
    LaunchedEffect(navigation) {
        navigation.internalNavigationState.listen()
            .filterIsInstance<NavigationEvent.Removed>()
            .collect { event ->
                saveableStateHolder.removeState(event.route)
            }
    }
}