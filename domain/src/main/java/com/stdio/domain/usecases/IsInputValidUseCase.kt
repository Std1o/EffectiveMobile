package com.stdio.domain.usecases

import androidx.core.util.PatternsCompat
import javax.inject.Inject

class IsInputValidUseCase @Inject constructor(){
    operator fun invoke(email: String, password: String): Boolean {
        return email.isNotEmpty()
                && password.isNotEmpty()
                && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}