package com.stdio.effectivemobile.ui.login

import androidx.lifecycle.ViewModel
import com.stdio.domain.usecases.IsInputValidUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val isInputValidUseCase: IsInputValidUseCase
) : ViewModel() {

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()


    fun checkIsValid(email: String, password: String) {
        _isButtonEnabled.value = isInputValidUseCase(email = email, password = password)
    }
}