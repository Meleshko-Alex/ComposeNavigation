package com.meleha.composenavigation.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.meleha.composenavigation.R
import com.meleha.navigation.Screen
import com.meleha.navigation.ScreenEnvironment

@Stable
interface AppScreen : Screen {
    override val environment: AppScreenEnvironment
}

@Stable
class AppScreenEnvironment : ScreenEnvironment {
    var titleRes: Int by mutableStateOf(R.string.app_name)
    var icon: ImageVector? by mutableStateOf(null)
    var floatingAction: FloatingAction? by mutableStateOf(null)
}

@Immutable
data class FloatingAction(
    val icon: ImageVector,
    val onClick: () -> Unit,
)