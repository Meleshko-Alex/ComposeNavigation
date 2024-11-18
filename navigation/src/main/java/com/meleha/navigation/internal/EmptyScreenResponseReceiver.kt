package com.meleha.navigation.internal

import com.meleha.navigation.ScreenResponseReceiver
import kotlin.reflect.KClass

internal object EmptyScreenResponseReceiver : ScreenResponseReceiver {
    override fun <T : Any> get(clazz: KClass<T>): T? = null
}