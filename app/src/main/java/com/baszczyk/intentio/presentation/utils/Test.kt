package com.baszczyk.intentio.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    val list = (1..100).toList()
    CoroutineScope(Dispatchers.Default).launch {
        delay(1000L)
        list.inlinedForEach {
            delay(1000L)
            println(it)
        }
    }

    CoroutineScope(Dispatchers.Default).launch {
        delay(1000L)
        list.normalForEach {
          //  delay(1000L)
            println(it)
        }
    }


}

fun <T> List<T>.normalForEach(action: (T) -> Unit) {
    for(item in this) {
        action(item)
    }
}

inline fun <T> List<T>.inlinedForEach(action: (T) -> Unit) {
    for(item in this) {
        action(item)
    }
}

inline fun <reified T> T.printClassName() {
    println(T::class.simpleName)
}