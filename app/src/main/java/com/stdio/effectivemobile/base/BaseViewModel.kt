package com.stdio.effectivemobile.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdio.domain.model.LoadableData
import com.stdio.effectivemobile.common.InvalidArgumentException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KType
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.reflect

@Suppress("UNCHECKED_CAST")
open class BaseViewModel : ViewModel() {
    /**
     * Method for calling LoadableData requests, that automatically sets Loading status
     * @param call lambda that returns LoadableData or parent of LoadableData
     */
    protected fun <State> loadData(
        call: suspend () -> State
    ): StateFlow<State> {
        val kType = call.reflect()?.returnType
        if (isLoadableData(kType) == false) {
            throw InvalidArgumentException(
                expected = "LoadableData or LoadableData superclass",
                found = kType,
                advice = if (isFlow(kType)) "Use loadDataFlow() instead" else ""
            )
        }
        val stateFlow = MutableStateFlow(LoadableData.Loading as State)
        viewModelScope.launch {// for asynchrony
            val requestResult: State = call()
            stateFlow.emit(requestResult)
        }
        return stateFlow
    }

    /**
     * Method for calling LoadableData requests, that automatically sets Loading status
     * @param call lambda that returns flow of LoadableData or parent of LoadableData
     */
    protected fun <State> loadDataFlow(
        call: suspend () -> Flow<State>
    ): StateFlow<State> {
        val kType = call.reflect()?.returnType
        if (isFlowOfLoadableData(kType) == false) {
            throw InvalidArgumentException(
                expected = "Flow of LoadableData or LoadableData superclass",
                found = kType
            )
        }
        val stateFlow = MutableStateFlow(LoadableData.Loading as State)
        viewModelScope.launch {// for asynchrony
            call().collect {
                stateFlow.emit(it)
            }
        }
        return stateFlow
    }

    private fun isLoadableData(kType: KType?) =
        kType?.jvmErasure?.isSuperclassOf(LoadableData::class)

    private fun isFlow(kType: KType?) = kType?.classifier == Flow::class

    private fun isFlowOfLoadableData(kType: KType?) =
        kType?.arguments?.first()?.type?.jvmErasure?.isSuperclassOf(
            LoadableData::class
        )
}