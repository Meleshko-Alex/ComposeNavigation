package com.meleha.composenavigation.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.meleha.composenavigation.AppRoute
import com.meleha.composenavigation.RootTabs
import com.meleha.navigation.NavigationState

@Composable
fun AppNavigatorBar(
    navigationState: NavigationState,
    onTabSelected: (AppRoute) -> Unit
) {
    if (navigationState.isRoot) {
        NavigationBar {
            RootTabs.forEach { tab ->
                val environment = remember(tab) {
                    tab.screenProducer().environment
                }
                val icon = environment.icon
                if (icon != null) {
                    NavigationBarItem(
                        selected = navigationState.currentRoute == tab,
                        label = { Text(text = stringResource(id = environment.titleRes)) },
                        onClick = { onTabSelected(tab) },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = stringResource(id = environment.titleRes)
                            )
                        }
                    )
                }
            }
        }
    }
}