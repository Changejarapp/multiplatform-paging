package com.jar.multiplatform.paging.composeMultiplatform.helpers

import androidx.paging.PagingData
import androidx.paging.insertSeparators

fun <T : Any> PagingData<T>.insertPagingSeparators(
    predicate: suspend (before: T?, after: T?) -> T?
): PagingData<T> {
    return this.insertSeparators { before, after ->
        predicate(before, after)
    }
}