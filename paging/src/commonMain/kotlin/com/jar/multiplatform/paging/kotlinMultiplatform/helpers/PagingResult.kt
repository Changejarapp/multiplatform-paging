package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

data class MultiplatformPagingResult<K, V>(
    val items: List<V>,
    val currentKey: K,
    val prevKey: () -> K?,
    val nextKey: () -> K?,
)