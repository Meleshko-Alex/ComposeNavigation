package com.meleha.composenavigation.ui.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.meleha.composenavigation.AppRoute
import com.meleha.composenavigation.R
import com.meleha.navigation.Route

@Composable
fun AppFloatingActionButton(
    currentRoute: Route,
    onClick: () -> Unit
) {
    if (currentRoute == AppRoute.Tab.Items) {
        FloatingActionButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_new_item)
            )
        }
    }
}