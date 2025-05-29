package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


expect class MultiplatformPagingData<T : Any>

expect suspend fun <T : Any>  MultiplatformPagingData<T>.filter(predicate: suspend (T) -> Boolean):  MultiplatformPagingData<T>

expect suspend fun <T : Any, R : Any>  MultiplatformPagingData<T>.map(transform: suspend (T) -> R):  MultiplatformPagingData<R>

expect suspend fun <T : Any, R : Any>  MultiplatformPagingData<T>.flatMap(transform: suspend (T) -> Iterable<R>):  MultiplatformPagingData<R>

expect suspend fun <T : Any>  MultiplatformPagingData<T>.insertPagingSeparators(predicate: suspend (before: T?, after: T?) -> T?):  MultiplatformPagingData<T>

expect fun <T : Any> Flow<MultiplatformPagingData<T>>.cachedIn(scope: CoroutineScope): Flow<MultiplatformPagingData<T>>