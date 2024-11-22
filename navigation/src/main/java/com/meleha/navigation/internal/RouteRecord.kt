package com.meleha.navigation.internal

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.meleha.navigation.Route
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Immutable
@Parcelize
data class RouteRecord(
    val uuid: String,
    val route: Route
) : Parcelable {
    constructor(route: Route) : this(
        uuid = UUID.randomUUID().toString(),
        route = route
    )
}