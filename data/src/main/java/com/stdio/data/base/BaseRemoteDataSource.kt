package com.stdio.data.base

import com.stdio.data.util.EncodeErrorUtil
import com.stdio.domain.model.LoadableData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRemoteDataSource {

    /**
     * Generates LoadableData that contains a limited set of loading data states
     */
    protected suspend fun <T> loadData(
        call: suspend () -> Response<T>
    ): LoadableData<T> {
        try {
            val response = withContext(Dispatchers.IO) { call() }
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return LoadableData.Success(body)
                }
            }
            val errorMessage = EncodeErrorUtil.encodeErrorCode(response.errorBody())
            return loadError(errorMessage, response.code())
        } catch (e: Exception) {
            return loadError(e.message ?: " ", -1)
        }
    }

    private fun <T> loadError(errorMessage: String, code: Int): LoadableData<T> =
        LoadableData.Error(errorMessage, code)
}