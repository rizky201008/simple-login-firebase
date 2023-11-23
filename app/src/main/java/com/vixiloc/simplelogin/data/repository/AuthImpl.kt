package com.vixiloc.simplelogin.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.vixiloc.simplelogin.data.remote.dto.LoginResponseDto
import com.vixiloc.simplelogin.domain.data.UserCredential
import com.vixiloc.simplelogin.domain.repository.Auth

class AuthImpl(private val auth: FirebaseAuth) : Auth {
    override suspend fun login(user: UserCredential): LoginResponseDto {
        var message = ""
        auth.signInWithEmailAndPassword(user.email!!, user.password!!)
            .addOnCompleteListener { task ->
                message = if (task.isSuccessful) {
                    "success"
                } else {
                    "failed"
                }
            }
        return LoginResponseDto(message = message)
    }
}