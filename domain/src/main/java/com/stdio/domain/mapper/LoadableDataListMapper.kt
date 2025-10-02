package com.stdio.domain.mapper

import com.stdio.domain.model.LoadableData

abstract class LoadableDataListMapper<I, O> :
    Mapper<LoadableData<List<I>>, LoadableData<List<O>>> {
    override fun map(input: LoadableData<List<I>>): LoadableData<List<O>> =
        when (input) {
            is LoadableData.NoState -> LoadableData.NoState
            is LoadableData.Error -> LoadableData.Error(
                input.exception,
                input.code,
            )

            is LoadableData.Loading -> LoadableData.Loading
            is LoadableData.Success -> LoadableData.Success(input.data.map(::getSuccess))
        }

    protected abstract fun getSuccess(input: I): O
}