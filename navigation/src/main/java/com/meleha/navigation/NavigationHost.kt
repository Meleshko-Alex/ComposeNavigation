package com.meleha.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.meleha.navigation.internal.EmptyRouter

val LocalRouter = staticCompositionLocalOf<Router> { EmptyRouter }

@Composable
fun NavigationHost(
    navigation: Navigation,
    modifier: Modifier = Modifier,
    routerMapper: @Composable (Route) -> Unit
) {
    val (router, navigationState) = navigation
    BackHandler(enabled = !navigationState.isRoot) {
        router.pop()
    }
    Box(modifier = modifier) {
        CompositionLocalProvider(
            LocalRouter provides router
        ) {
            routerMapper.invoke(navigationState.currentRoute)
        }
    }
}