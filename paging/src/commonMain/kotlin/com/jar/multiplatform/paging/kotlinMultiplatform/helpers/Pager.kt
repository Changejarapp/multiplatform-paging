package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow


@FlowPreview
@ExperimentalCoroutinesApi
expect class MultiplatformPager<K: Any, V: Any>(
    clientScope: CoroutineScope,
    config: MultiplatformPagingConfig,
    initialKey: K,
    getItems: suspend (K, Int) -> MultiplatformPagingResult<K, V>
) {
    val pagingData: Flow<MultiplatformPagingData<V>>

    fun refresh()
}