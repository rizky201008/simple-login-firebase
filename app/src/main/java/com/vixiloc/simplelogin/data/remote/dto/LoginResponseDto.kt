package com.vixiloc.simplelogin.data.remote.dto

import com.vixiloc.simplelogin.domain.data.LoginResponse

data class LoginResponseDto(
    val message: String
)


fun LoginResponseDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        message = message
    )
}