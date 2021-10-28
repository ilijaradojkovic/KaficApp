package com.example.kaficapp.data

sealed class RespondHandler<out T>(val data:T?=null,val message:String=""){
    class Loading<T>:RespondHandler<T>()
    class Success<T>(data: T?,message: String):RespondHandler<T>(data,message)
    class Error<T>(message: String):RespondHandler<T>(message = message)
}
