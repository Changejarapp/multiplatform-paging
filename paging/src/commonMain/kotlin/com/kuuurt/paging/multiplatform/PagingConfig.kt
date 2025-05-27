package com.kuuurt.paging.multiplatform


/**
 * Copyright 2020, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/11/2020
 */

expect class PagingConfig(
    pageSize: Int = K.MAX_SIZE_UNBOUNDED,
    prefetchDistance: Int = pageSize,
    enablePlaceholders: Boolean,
    initialLoadSize: Int = pageSize * K.DEFAULT_INITIAL_PAGE_MULTIPLIER,
    maxSize: Int = K.MAX_SIZE_UNBOUNDED,
    jumpThreshold: Int = K.COUNT_UNDEFINED
)

object K {
    internal const val MAX_SIZE_UNBOUNDED: Int = Int.MAX_VALUE
    internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
    internal const val COUNT_UNDEFINED: Int = Int.MIN_VALUE
}
