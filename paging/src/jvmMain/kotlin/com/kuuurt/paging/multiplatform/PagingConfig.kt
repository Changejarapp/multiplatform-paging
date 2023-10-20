package com.kuuurt.paging.multiplatform

import androidx.paging.PagingConfig as AndroidXPagingConfig

/**
 * Copyright 2020, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/11/2020
 */

//actual typealias PagingConfig = AndroidXPagingConfig
actual class PagingConfig actual constructor(
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