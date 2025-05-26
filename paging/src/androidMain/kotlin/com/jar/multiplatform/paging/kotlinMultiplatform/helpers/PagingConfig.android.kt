package com.jar.multiplatform.paging.kotlinMultiplatform.helpers
import androidx.paging.PagingConfig as AndroidXPagingConfig

actual class MultiplatformPagingConfig actual constructor(
    pageSize: Int,
    prefetchDistance: Int,
    enablePlaceholders: Boolean,
    initialLoadSize: Int,
    maxSize: Int,
    jumpThreshold: Int
) {
    val androidConfig: AndroidXPagingConfig = AndroidXPagingConfig(
        pageSize = pageSize,
        prefetchDistance = prefetchDistance,
        enablePlaceholders = enablePlaceholders,
        initialLoadSize = initialLoadSize,
        maxSize = maxSize,
        jumpThreshold = jumpThreshold
    )
}