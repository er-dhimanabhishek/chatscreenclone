package com.example.quicksellandroidtakehomeassignment.model

sealed class Result<T>(val chatList: T? = null, val errorMsg: String? = null){

    class Loading<T>(): Result<T>()

    class Success<T>(chatList: T? = null): Result<T>(chatList = chatList)

    class Error<T>(error: String? = null): Result<T>(errorMsg = error)

}
