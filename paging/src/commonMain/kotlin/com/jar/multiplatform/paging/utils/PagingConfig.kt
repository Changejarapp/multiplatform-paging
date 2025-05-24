package com.jar.multiplatform.paging.utils

import androidx.paging.PagingConfig

class PagingConfig constructor(
    pageSize: Int,
    prefetchDistance: Int = 10,
    enablePlaceholders: Boolean,
    initialLoadSize: Int,
    maxSize: Int = Int.MAX_VALUE,
    jumpThreshold: Int = Int.MIN_VALUE
) {
    val androidConfig: PagingConfig = PagingConfig(
        pageSize = pageSize,
        prefetchDistance = prefetchDistance,
        enablePlaceholders = enablePlaceholders,
        initialLoadSize = initialLoadSize,
        maxSize = maxSize,
        jumpThreshold = jumpThreshold
    )
}