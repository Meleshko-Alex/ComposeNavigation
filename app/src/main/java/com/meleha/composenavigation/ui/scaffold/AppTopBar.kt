package com.meleha.composenavigation.ui.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.meleha.composenavigation.AppRoute
import com.meleha.composenavigation.R
import com.meleha.navigation.NavigationState
import com.meleha.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navigationState: NavigationState,
    onNavigationClick: () -> Unit,
    onAboutClick: () -> Unit,
    onClearClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(
                    (navigationState.currentRoute as? AppRoute)?.titleRes ?: R.string.app_name
                ),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = if (navigationState.isRoot) Icons.Default.Menu else Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.main_menu)
                )
            }
        },
        actions = {
            var showPopupMenu by remember { mutableStateOf(false) }
            IconButton(onClick = { showPopupMenu = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_actions)
                )
            }
            DropdownMenu(
                expanded = showPopupMenu,
                onDismissRequest = { showPopupMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.about)) },
                    onClick = {
                        onAboutClick()
                        showPopupMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.clear)) },
                    onClick = {
                        onClearClick()
                        showPopupMenu = false
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAppTopBar() {
    val mockNavigationState = object : NavigationState {
        override val isRoot: Boolean = false
        override val currentRoute: Route = AppRoute.Tab.Items
    }

    AppTopBar(
        navigationState = mockNavigationState,
        onNavigationClick = {  },
        onAboutClick = {  },
        onClearClick = {  }
    )
}