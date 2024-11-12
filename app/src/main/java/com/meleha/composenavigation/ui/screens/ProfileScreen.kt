package com.meleha.composenavigation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.meleha.composenavigation.R
import com.meleha.composenavigation.ui.AppScreen
import com.meleha.composenavigation.ui.AppScreenEnvironment

val ProfileScreenProducer = { ProfileScreen() }

class ProfileScreen : AppScreen {
    override val environment = AppScreenEnvironment().apply {
        titleRes = R.string.profile
        icon = Icons.Default.AccountBox
    }

    @Composable
    override fun Content() {
        Text(
            text = "Profile Screen",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    }
    /*@Composable
    @Preview(showSystemUi = true)
    fun ProfilePreview() {
        Content()
    }*/
}