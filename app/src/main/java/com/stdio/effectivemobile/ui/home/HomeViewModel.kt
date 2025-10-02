package com.stdio.effectivemobile.ui.home

import androidx.lifecycle.viewModelScope
import com.stdio.domain.model.LoadableData
import com.stdio.domain.repository.CoursesRepository
import com.stdio.effectivemobile.base.BaseViewModel
import com.stdio.core.common.flow.SingleEventFlow
import com.stdio.effectivemobile.model.CoursesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CoursesRepository) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(CoursesUIState())
    val uiState = _uiState.asStateFlow()

    // т.к. ошибка показывается snackbar'ом, нужно чтобы вызыывалось только один раз
    private val _errorFlow = SingleEventFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            loadData { repository.getCourses() }.collect { courses ->
                if (courses is LoadableData.Error) {
                    _errorFlow.emit(courses.exception)
                } else {
                    _uiState.update { it.copy(courses = courses) }
                }
            }
        }
    }
}