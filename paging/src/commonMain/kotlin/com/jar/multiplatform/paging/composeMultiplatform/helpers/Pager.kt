package com.jar.multiplatform.paging.composeMultiplatform.helpers

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
@ExperimentalCoroutinesApi
class Pager<K : Any, V : Any> constructor(
    clientScope: CoroutineScope,
    config: PagingConfig,
    initialKey: K,
    getItems: suspend (K, Int) -> PagingResult<K, V>
) {
    private var source: PagingSource<K, V>? = null

    val pagingData: Flow<PagingData<V>> = Pager(
        config = config.androidConfig,
        pagingSourceFactory = {
            PagingSource(
                initialKey,
                getItems
            ).also { source = it }
        }
    ).flow

    class PagingSource<K : Any, V : Any>(
        private val initialKey: K,
        private val getItems: suspend (K, Int) -> PagingResult<K, V>
    ) : androidx.paging.PagingSource<K, V>() {

        override val jumpingSupported: Boolean
            get() = true

        override val keyReuseSupported: Boolean
            get() = true

        override fun getRefreshKey(state: PagingState<K, V>): K? {
            return null
        }

        override suspend fun load(params: LoadParams<K>): LoadResult<K, V> {
            val currentKey = params.key ?: initialKey
            return try {
                val pagingResult = getItems(currentKey, params.loadSize)
                LoadResult.Page(
                    data = pagingResult.items,
                    prevKey = if (currentKey == initialKey) null else pagingResult.prevKey(),
                    nextKey = if (pagingResult.items.isEmpty()) null else pagingResult.nextKey()
                )
            } catch (exception: Exception) {
                return LoadResult.Error(exception)
            }
        }
    }

    fun refresh() {
        source?.invalidate()
    }
}