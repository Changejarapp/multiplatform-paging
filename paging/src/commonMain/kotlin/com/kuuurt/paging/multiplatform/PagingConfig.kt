package com.kuuurt.paging.multiplatform


/**
 * Copyright 2020, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 06/11/2020
 */

expect class PagingConfig(
    pageSize: Int,
    prefetchDistance: Int,
    enablePlaceholders: Boolean,
    initialLoadSize: Int,
    maxSize: Int,
    jumpThreshold: Int
)