package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

import androidx.paging.PagingConfig


expect class MultiplatformPagingConfig(
    pageSize: Int,
    prefetchDistance: Int = 10,
    enablePlaceholders: Boolean,
    initialLoadSize: Int,
    maxSize: Int = Int.MAX_VALUE,
    jumpThreshold: Int = Int.MIN_VALUE
)