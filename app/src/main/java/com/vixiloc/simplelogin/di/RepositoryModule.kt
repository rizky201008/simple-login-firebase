package com.vixiloc.simplelogin.di

import com.vixiloc.simplelogin.data.repository.AuthImpl
import com.vixiloc.simplelogin.domain.repository.Auth
import org.koin.dsl.module

val repositoryModule = module {
    factory<Auth> {
        AuthImpl(auth = get())
    }
}