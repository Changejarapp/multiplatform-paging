package com.jar.multiplatform.paging.kotlinMultiplatform.helpers
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.insertSeparators
import androidx.paging.flatMap
import androidx.paging.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData as AndroidXPagingData

actual typealias MultiplatformPagingData<T> = AndroidXPagingData<T>

actual suspend fun <T : Any> MultiplatformPagingData<T>.filter(predicate: suspend (T) -> Boolean): MultiplatformPagingData<T> {
    return this.filter(predicate)
}

actual suspend fun <T : Any> MultiplatformPagingData<T>.insertPagingSeparators(
    predicate: suspend (before: T?, after: T?) -> T?
): MultiplatformPagingData<T> {
    return this.insertSeparators { before, after ->
        predicate(before, after)
    }
}

actual suspend fun <T : Any, R : Any> MultiplatformPagingData<T>.map(transform: suspend (T) -> R): AndroidXPagingData<R> {
    return this.map(transform)
}

actual suspend fun <T : Any, R : Any> MultiplatformPagingData<T>.flatMap(transform: suspend (T) -> Iterable<R>): AndroidXPagingData<R> {
    return this.flatMap(transform)
}

actual fun <T : Any> Flow<MultiplatformPagingData<T>>.cachedIn(
    scope: CoroutineScope
): Flow<MultiplatformPagingData<T>> {
    return this.cachedIn(scope)
}