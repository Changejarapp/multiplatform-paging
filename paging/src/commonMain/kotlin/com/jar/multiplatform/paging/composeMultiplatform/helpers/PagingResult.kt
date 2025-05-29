package com.jar.multiplatform.paging.composeMultiplatform.helpers

data class PagingResult<K, V>(
    val items: List<V>,
    val currentKey: K,
    val prevKey: () -> K?,
    val nextKey: () -> K?,
)