package com.stdio.core.common.flow

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Метод подписывается на получение данных и отписывается от них в зависимости от жизненного цикла.
 * По умолчанию, значения начинают поступать при достижении состояния STARTED, а отписывается в
 * состоянии STOPED. Это достаточно для большинства случаев, но вы можете поменять это поведение,
 * передав свое значение в параметр state.
 */
fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    result: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(state) {
            collect {
                result.invoke(it)
            }
        }
    }
}