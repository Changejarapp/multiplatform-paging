package com.jar.multiplatform.paging.utils

import androidx.paging.PagingData
import androidx.paging.insertSeparators

typealias JarMultiplatformPagingData<T> = PagingData<T>

fun <T : Any> PagingData<T>.insertPagingSeparators(
    predicate: suspend (before: T?, after: T?) -> T?
): PagingData<T> {
    return this.insertSeparators { before, after ->
        predicate(before, after)
    }
}