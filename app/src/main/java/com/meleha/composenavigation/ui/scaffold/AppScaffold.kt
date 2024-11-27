package com.meleha.composenavigation.ui.scaffold

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.meleha.composenavigation.R
import com.meleha.composenavigation.RootTabs
import com.meleha.composenavigation.di.injectViewModel
import com.meleha.composenavigation.ui.AppDeepLinkHandler
import com.meleha.composenavigation.ui.AppScreenEnvironment
import com.meleha.composenavigation.MainViewModel
import com.meleha.navigation.NavigationHost
import com.meleha.navigation.rememberNavigation

@Composable
fun AppScaffold() {
    val context = LocalContext.current
    val viewModel = injectViewModel<MainViewModel>()
    val navigation = rememberNavigation(RootTabs, deepLinkHandler = AppDeepLinkHandler)
    val (router, navigationState) = navigation
    val environment = navigationState.currentScreen.environment as AppScreenEnvironment

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
                onClearClick = viewModel::clear
            )
        },
        floatingActionButton = {
            AppFloatingActionButton(environment.floatingAction)
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            AppNavigatorBar(
                navigationState = navigationState,
                currentIndex = navigationState.currentStackIndex,
                onIndexSelected = router::switchStack
            )
        }
    ) { paddingValues ->
        NavigationHost(
            navigation = navigation,
            modifier = Modifier.padding(paddingValues)
        )
    }
}