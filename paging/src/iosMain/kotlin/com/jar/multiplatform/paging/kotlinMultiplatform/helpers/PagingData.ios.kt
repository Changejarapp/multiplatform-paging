package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

actual class MultiplatformPagingData<T : Any> internal constructor(items: List<T>) : List<T> by items

internal fun <T: Any> List<T>.toPagingData(): MultiplatformPagingData<T> =
    MultiplatformPagingData(this)

actual suspend fun <T : Any> MultiplatformPagingData<T>.filter(predicate: suspend (T) -> Boolean): MultiplatformPagingData<T> {
    return (this@filter as List<T>).filter {
        predicate(it)
    } as MultiplatformPagingData<T>
}

actual suspend fun <T : Any, R : Any> MultiplatformPagingData<T>.map(transform: suspend (T) -> R): MultiplatformPagingData<R> {
    return (this@map as List<T>).map {
        transform(it)
    } as MultiplatformPagingData<R>
}

actual suspend fun <T : Any, R : Any> MultiplatformPagingData<T>.flatMap(transform: suspend (T) -> Iterable<R>): MultiplatformPagingData<R> {
    return (this@flatMap as List<T>).flatMap {
        transform(it)
    } as MultiplatformPagingData<R>
}

actual suspend fun <T : Any> MultiplatformPagingData<T>.insertPagingSeparators(predicate: suspend (before: T?, after: T?) -> T?): MultiplatformPagingData<T> {
    val list = (this@insertPagingSeparators as List<T>).toMutableList()
    val listIterator = list.listIterator()
    var previous: T? = null
    while (listIterator.hasNext()) {
        previous = if (listIterator.hasPrevious()) {
            val prev = listIterator.previous() // pre
            listIterator.next() // curr
            prev
        } else null
        val next = if (listIterator.hasNext()) { listIterator.next() } else null //next
        if(next!=null && listIterator.hasPrevious()) listIterator.previous() //curr
        val header = predicate(previous, next)
        header?.let {
            listIterator.add(it)
        }
        if (listIterator.hasNext()) listIterator.next() //next
    }
    return list.toPagingData()
}

actual fun <T : Any> Flow<MultiplatformPagingData<T>>.cachedIn(
    scope: CoroutineScope
): Flow<MultiplatformPagingData<T>> {
    return this
}