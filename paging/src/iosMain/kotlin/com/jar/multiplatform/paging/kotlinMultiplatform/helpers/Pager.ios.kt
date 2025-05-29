package com.jar.multiplatform.paging.kotlinMultiplatform.helpers

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
actual class MultiplatformPager<K : Any, V : Any> actual constructor(
    private val clientScope: CoroutineScope,
    private val config: MultiplatformPagingConfig,
    private val initialKey: K,
    private val getItems: suspend (K, Int) -> MultiplatformPagingResult<K, V>
) {

    private val _pagingData = MutableStateFlow<MultiplatformPagingData<V>?>(null)
    actual val pagingData: Flow<MultiplatformPagingData<V>> = _pagingData.filterNotNull()

    private val _pagingState = MutableStateFlow<PagingState>(PagingState.Success)
    val pagingState: Flow<PagingState> = _pagingState.filterNotNull()

    private val _hasNextPage = MutableStateFlow(true)
    val hasNextPage: Boolean
        get() = _hasNextPage.value

    private val _totalSize = MutableStateFlow(0)
    val totalSize: Int
        get() = _totalSize.value

    private val currentPagingResult: MutableStateFlow<MultiplatformPagingResult<K, V>?> = MutableStateFlow(null)

    init {
        loadNext()
    }

    actual fun refresh() {
        currentPagingResult.value = null
        _hasNextPage.value = true
        loadNext()
    }

    fun loadPrevious() {
        loadItems(LoadDirection.PREVIOUS)
    }

    fun loadNext() {
        loadItems(LoadDirection.NEXT)
    }

    private fun loadItems(loadDirection: LoadDirection) {
        val pagingResult = currentPagingResult.value
        val key = if (pagingResult == null) {
            initialKey
        } else {
            when (loadDirection) {
                LoadDirection.NEXT -> pagingResult.nextKey()
                LoadDirection.PREVIOUS -> pagingResult.prevKey()
            }
        }

        if (key != null && hasNextPage) {
            clientScope.launch(CoroutineExceptionHandler { _, throwable ->
                _pagingState.value = PagingState.Error(throwable)
            }) {
                _pagingState.value = if (currentPagingResult.value?.items?.isEmpty() == true) {
                    PagingState.LoadingInitial
                } else {
                    PagingState.LoadingMore
                }
                val newPagingResult = getItems(key, config.pageSize)
                _pagingData.value = _pagingData.value?.toMutableList()?.apply {
                    addAll(newPagingResult.items)
                }?.toPagingData() ?: newPagingResult.items.toPagingData()
                _hasNextPage.value = newPagingResult.items.size >= config.pageSize
                currentPagingResult.value = newPagingResult
                _pagingState.value = PagingState.Success
                _totalSize.value = _pagingData.value?.size ?: 0
            }
        }
    }

    enum class LoadDirection {
        PREVIOUS,
        NEXT
    }

    fun getConfigPageSize(): Int {
        return config.pageSize
    }
}

sealed interface PagingState {
    object Success : PagingState
    object LoadingMore : PagingState
    object LoadingInitial : PagingState
    data class Error(val error: Throwable) : PagingState
}