package com.meleha.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import com.meleha.navigation.internal.InternalNavigationState
import com.meleha.navigation.internal.RouteRecord
import com.meleha.navigation.internal.ScreenMultiStack
import com.meleha.navigation.internal.ScreenStack
import com.meleha.navigation.links.DeepLinkHandler
import com.meleha.navigation.links.MultiStackState
import com.meleha.navigation.links.StackState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class Navigation(
    val router: Router,
    val navigationState: NavigationState,
    internal val internalNavigationState: InternalNavigationState
)

@Composable
fun rememberNavigation(
    rootRoutes: ImmutableList<Route>,
    initialIndex: Int = 0,
    deepLinkHandler: DeepLinkHandler = DeepLinkHandler.DEFAULT
): Navigation {
    val activity: Activity? = LocalContext.current as? Activity
    val screenStack = rememberSaveable(rootRoutes) {
        val inputState = MultiStackState(
            activeStackIndex = initialIndex,
            stacks = rootRoutes.map { rootRoute -> StackState(listOf(rootRoute)) }
        )

        val outputState = activity?.intent?.data?.let { deepLinkUri ->
            deepLinkHandler.handleDeepLink(deepLinkUri, inputState)
        } ?: inputState


        ScreenMultiStack(
            initialIndex = outputState.activeStackIndex,
            stacks = outputState.stacks.map { stackState ->
                ScreenStack(stackState.routes)
            }.toMutableStateList()
        )
    }

    return remember(rootRoutes) {
        Navigation(
            router = screenStack,
            navigationState = screenStack,
            internalNavigationState = screenStack
        )
    }
}