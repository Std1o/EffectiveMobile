package com.stdio.domain.mapper

import com.stdio.domain.model.LoadableData

abstract class LoadableDataMapper<I, O> : Mapper<LoadableData<I>, LoadableData<O>> {
    override fun map(input: LoadableData<I>): LoadableData<O> =
        when (input) {
            is LoadableData.NoState -> LoadableData.NoState
            is LoadableData.Error -> LoadableData.Error(
                input.exception,
                input.code
            )

            is LoadableData.Loading -> LoadableData.Loading
            is LoadableData.Success -> LoadableData.Success(getSuccess(input))
        }

    protected abstract fun getSuccess(input: LoadableData.Success<I>): O
}