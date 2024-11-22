package com.meleha.composenavigation

import com.meleha.composenavigation.ui.AppScreen
import com.meleha.composenavigation.ui.screens.ItemScreenArgs
import com.meleha.composenavigation.ui.screens.ItemsScreenProducer
import com.meleha.composenavigation.ui.screens.ProfileScreenProducer
import com.meleha.composenavigation.ui.screens.SettingsScreenProducer
import com.meleha.composenavigation.ui.screens.itemScreenProducer
import com.meleha.navigation.Route
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

sealed class AppRoute(
    override val screenProducer: () -> AppScreen
) : Route {

    @Parcelize
    data class Item(
        val args: ItemScreenArgs
    ) : AppRoute(itemScreenProducer(args))

    sealed class Tab(
        screenProducer: () -> AppScreen
    ) : AppRoute(screenProducer) {
        @Parcelize data object Items : Tab(ItemsScreenProducer)
        @Parcelize data object Settings : Tab(SettingsScreenProducer)
        @Parcelize data object Profile : Tab(ProfileScreenProducer)
    }
}

val RootTabs =
    persistentListOf(AppRoute.Tab.Items, AppRoute.Tab.Settings, AppRoute.Tab.Profile)