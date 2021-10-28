package com.example.kaficapp

import com.example.kaficapp.data.LoginInfo
import com.example.kaficapp.data.RespondInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class Repository @Inject constructor(private val api: API) {
   suspend fun tryToLogin(loginInfo: LoginInfo): Flow<RespondInfo> {
        return flowOf(api.tryToLogin(loginInfo))
    }
    suspend fun  tryToSignUp(loginInfo: LoginInfo):Flow<RespondInfo>{
        return flowOf(api.tryToSignUp(loginInfo))
    }
    suspend fun getAllDrinkTypes()= flowOf(api.getAllDrinkTypes())
    suspend fun getAllDrinksByType(type:Int)= flowOf(api.getAllDrinksByType(type))

    suspend fun getAllCakesTypes()= flowOf(api.getAllCakeTypes())
    suspend fun getAllCakesByType(type:Int)= flowOf(api.getAllCakesByType(type))
}