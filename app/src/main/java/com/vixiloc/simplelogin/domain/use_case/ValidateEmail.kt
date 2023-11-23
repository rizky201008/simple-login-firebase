package com.vixiloc.simplelogin.domain.use_case

import android.content.Context
import android.util.Patterns
import com.vixiloc.simplelogin.R

class ValidateEmail(private val context: Context) {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.email_blank_error)
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.email_invalid_error)
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}