package com.meleha.composenavigation.ui.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.meleha.composenavigation.RootTabs
import com.meleha.navigation.NavigationState

@Composable
fun AppNavigatorBar(
    currentIndex: Int,
    navigationState: NavigationState,
    onIndexSelected: (Int) -> Unit
) {
    NavigationBar {
        RootTabs.forEachIndexed { index, tab ->
            val environment = remember(tab) {
                tab.screenProducer().environment
            }
            val icon = environment.icon
            if (icon != null) {
                NavigationBarItem(
                    selected = currentIndex == index,
                    label = { Text(text = stringResource(id = environment.titleRes)) },
                    onClick = { onIndexSelected(index) },
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