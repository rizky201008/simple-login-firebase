package com.vixiloc.simplelogin.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.vixiloc.simplelogin.data.remote.dto.LoginResponseDto
import com.vixiloc.simplelogin.domain.data.UserCredential
import com.vixiloc.simplelogin.domain.repository.Auth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthImpl(private val auth: FirebaseAuth) : Auth {
    override suspend fun login(user: UserCredential): LoginResponseDto {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(LoginResponseDto(message = "success"))
                    } else {
                        continuation.resume(LoginResponseDto(message = "error"))
                    }
                }
        }
    }
}
