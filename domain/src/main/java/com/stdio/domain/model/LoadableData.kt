package com.stdio.domain.model

sealed interface LoadableData<out R> {
    data object NoState : LoadableData<Nothing>

    data class Success<out T>(
        val data: T,
    ) : LoadableData<T>

    data class Error(
        val exception: String,
        val code: Int = -1,
    ) : LoadableData<Nothing>

    data object Loading : LoadableData<Nothing>
}