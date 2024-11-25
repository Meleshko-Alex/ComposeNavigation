package com.meleha.navigation.links

import android.net.Uri

fun interface DeepLinkHandler {

    fun handleDeepLink(
        uri: Uri,
        inputState: MultiStackState
    ): MultiStackState

    companion object {
        val DEFAULT = DeepLinkHandler { uri, inputState -> inputState  }
    }
}