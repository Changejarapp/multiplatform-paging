package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

actual class MultiplatformPagingConfig actual constructor(
    val pageSize: Int,
    val prefetchDistance: Int,
    val enablePlaceholders: Boolean,
    val initialLoadSize: Int,
    val maxSize: Int,
    val jumpThreshold: Int
)