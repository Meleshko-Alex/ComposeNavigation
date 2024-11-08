package com.meleha.composenavigation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.meleha.composenavigation.ItemsRepository
import com.meleha.composenavigation.R

@Composable
fun ItemsScreen() {
    val itemsRepository = ItemsRepository.get()
    val items by itemsRepository.getItems().collectAsStateWithLifecycle()
    val isEmpty by remember {
        derivedStateOf { items.isEmpty() }
    }
    ItemsContent(isItemEmpty = isEmpty, items = { items })
}

@Composable
fun ItemsContent(
    isItemEmpty: Boolean,
    items: () -> List<String>
) {
    if (isItemEmpty) {
        Text(
            text = stringResource(R.string.no_items),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items.invoke()) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(all = 8.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ItemsPreview() {
    ItemsContent (false) { listOf("aaa", "bbb") }
}