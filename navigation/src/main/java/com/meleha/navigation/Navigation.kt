package com.meleha.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.meleha.navigation.internal.ScreenStack

@Stable
data class Navigation(
    val router: Router,
    val navigationState: NavigationState
)

@Composable
fun rememberNavigation(initialRoute: Route): Navigation {
    return remember(initialRoute) {
        val screenStack = ScreenStack(mutableStateListOf(initialRoute))
        Navigation(
            router = screenStack,
            navigationState = screenStack
        )
    }
}