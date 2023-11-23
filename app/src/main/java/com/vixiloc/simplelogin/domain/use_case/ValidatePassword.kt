package com.vixiloc.simplelogin.domain.use_case

import android.content.Context
import android.util.Patterns
import com.vixiloc.simplelogin.R

class ValidatePassword(private val context: Context) {

    fun execute(password: String): ValidationResult {
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.password_length_error)
            )
        }
//        val containsLettersAndDigits = password.any { it.isDigit() } &&
//                password.any { it.isLetter() }
//        if(!containsLettersAndDigits) {
//            return ValidationResult(
//                successful = false,
//                errorMessage = "The password needs to contain at least one letter and digit"
//            )
//        }
        return ValidationResult(
            successful = true
        )
    }
}