package com.vixiloc.simplelogin.domain.repository

import com.vixiloc.simplelogin.data.remote.dto.LoginResponseDto
import com.vixiloc.simplelogin.domain.data.UserCredential

interface Auth {
    suspend fun login(user: UserCredential): LoginResponseDto
}