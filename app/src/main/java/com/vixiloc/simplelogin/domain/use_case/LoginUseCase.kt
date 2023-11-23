package com.vixiloc.simplelogin.domain.use_case

import com.vixiloc.simplelogin.commons.Resource
import com.vixiloc.simplelogin.data.remote.dto.toLoginResponse
import com.vixiloc.simplelogin.domain.data.LoginResponse
import com.vixiloc.simplelogin.domain.data.UserCredential
import com.vixiloc.simplelogin.domain.repository.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val repo: Auth) {
    operator fun invoke(userCredential: UserCredential): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        val response = repo.login(userCredential).toLoginResponse()
        emit(Resource.Success(data = response))
    }
}