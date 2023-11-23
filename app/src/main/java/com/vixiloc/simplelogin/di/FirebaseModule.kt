package com.vixiloc.simplelogin.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.dsl.module

val firebaseModules = module {
    single {
        Firebase.auth
    }
}