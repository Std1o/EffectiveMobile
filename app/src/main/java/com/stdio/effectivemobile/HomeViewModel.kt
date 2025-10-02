package com.stdio.effectivemobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdio.domain.model.Course
import com.stdio.domain.repository.CoursesRepository
import com.stdio.effectivemobile.model.CoursesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CoursesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CoursesUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(courses = repository.getCourses()) }
        }
    }
}