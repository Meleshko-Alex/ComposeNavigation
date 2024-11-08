package com.meleha.composenavigation.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.meleha.composenavigation.AppRoute
import com.meleha.composenavigation.RootTabs
import com.meleha.navigation.LocalRouter
import com.meleha.navigation.Navigation
import com.meleha.navigation.NavigationState

@Composable
fun AppNavigatorBar(
    navigationState: NavigationState,
    onTabSelected: (AppRoute) -> Unit
) {
    if (navigationState.isRoot) {
        NavigationBar {
            RootTabs.forEach { tab ->
                NavigationBarItem(
                    selected = navigationState.currentRoute == tab,
                    label = { Text(text = stringResource(id = tab.titleRes)) },
                    onClick = { onTabSelected(tab) },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = stringResource(id = tab.titleRes)
                        )
                    }
                )
            }
        }
    }
}