package com.meleha.composenavigation.ui.scaffold

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.meleha.composenavigation.AppRoute
import com.meleha.composenavigation.ItemsRepository
import com.meleha.composenavigation.R
import com.meleha.composenavigation.ui.screens.AddItemScreen
import com.meleha.composenavigation.ui.screens.ItemsScreen
import com.meleha.composenavigation.ui.screens.ProfileScreen
import com.meleha.composenavigation.ui.screens.SettingsScreen
import com.meleha.navigation.NavigationHost
import com.meleha.navigation.rememberNavigation

@Composable
fun AppScaffold() {
    val context = LocalContext.current
    val itemsRepository: ItemsRepository = ItemsRepository.get()
    val navigation = rememberNavigation(initialRoute = AppRoute.Tab.Items)
    val (router, navigationState) = navigation

    Scaffold(
        topBar = {
            AppTopBar(
                navigationState = navigationState,
                onNavigationClick = { if (!navigationState.isRoot) router.pop() },
                onAboutClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.about_text), Toast.LENGTH_SHORT
                    ).show()
                },
                onClearClick = { itemsRepository.clear() }
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(
                currentRoute = navigationState.currentRoute,
                onClick = { router.launch(AppRoute.AddItem) }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            AppNavigatorBar(
                navigationState = navigationState,
                onTabSelected = { router.restart(it) }
            )
        }
    ) { paddingValues ->
        NavigationHost(
            navigation = navigation,
            modifier = Modifier.padding(paddingValues)
        ) { currentRoute ->
            when (currentRoute) {
                AppRoute.Tab.Items -> ItemsScreen()
                AppRoute.Tab.Profile -> SettingsScreen()
                AppRoute.Tab.Settings -> ProfileScreen()
                AppRoute.AddItem -> AddItemScreen()
            }
        }
    }
}