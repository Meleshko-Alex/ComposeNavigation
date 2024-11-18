package com.meleha.navigation.internal

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.os.ParcelCompat
import com.meleha.navigation.NavigationState
import com.meleha.navigation.Route
import com.meleha.navigation.Router
import com.meleha.navigation.Screen
import com.meleha.navigation.ScreenResponseReceiver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class ScreenStack(
    private val routes: SnapshotStateList<RouteRecord>,
    private val screenResponseBus: ScreenResponseBus = ScreenResponseBus()
    ) : NavigationState, Router, InternalNavigationState, Parcelable {

    override val isRoot: Boolean get() = routes.size == 1
    override val currentRoute: Route get() = routes.last().route
    override val currentUuid: String get() = routes.last().uuid
    override val currentScreen: Screen by derivedStateOf { currentRoute.screenProducer() }
    override val screenResponseReceiver: ScreenResponseReceiver = screenResponseBus

    private val eventsFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = Int.MAX_VALUE
    )

    constructor(parcel: Parcel) : this(
        SnapshotStateList<RouteRecord>().also { newList ->
            ParcelCompat.readList(
                parcel,
                newList,
                RouteRecord::class.java.classLoader,
                RouteRecord::class.java
            )
        }
    )

    override fun launch(route: Route) {
        screenResponseBus.cleanUp()
        routes.add(RouteRecord(route))
    }

    override fun pop(response: Any?) {
        val removedRoute = routes.removeLastOrNull()?.route
        if (removedRoute != null) {
            eventsFlow.tryEmit(NavigationEvent.Removed(removedRoute))
            if (response != null) {
                screenResponseBus.send(response)
            }
        }
    }

    override fun restart(route: Route) {
        screenResponseBus.cleanUp()
        routes.apply {
            routes.forEach {
                eventsFlow.tryEmit(NavigationEvent.Removed(it.route))
            }
            clear()
            add(RouteRecord(route))
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(routes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScreenStack> {
        override fun createFromParcel(parcel: Parcel): ScreenStack {
            return ScreenStack(parcel)
        }

        override fun newArray(size: Int): Array<ScreenStack?> {
            return arrayOfNulls(size)
        }
    }

    override fun listen(): Flow<NavigationEvent> {
        return eventsFlow
    }
}
