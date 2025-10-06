package com.stdio.effectivemobile.ui.home

import androidx.lifecycle.viewModelScope
import com.stdio.core.common.flow.SingleEventFlow
import com.stdio.domain.model.Course
import com.stdio.domain.model.LoadableData
import com.stdio.domain.repository.CoursesRepository
import com.stdio.domain.usecases.GetCoursesUseCase
import com.stdio.domain.usecases.ToggleFavoriteUseCase
import com.stdio.effectivemobile.base.BaseViewModel
import com.stdio.effectivemobile.model.CoursesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getCoursesUseCase: GetCoursesUseCase
) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(CoursesUIState())
    val uiState = _uiState.asStateFlow()

    // т.к. ошибка показывается snackbar'ом, нужно чтобы вызыывалось только один раз
    private val _errorFlow = SingleEventFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    init {
        getCourses()
    }

    fun getCourses() {
        viewModelScope.launch {
            loadData { getCoursesUseCase() }.collect { courses ->
                if (courses is LoadableData.Error) {
                    _errorFlow.emit(courses.exception)
                } else {
                    _uiState.update { it.copy(courses = courses) }
                }
            }
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
            getCourses()
        }
    }
}