package com.example.androidtakehomeassignment.retrofitapi

import com.example.androidtakehomeassignment.room.model.ChatEntity
import retrofit2.Response
import retrofit2.http.GET

interface ChatAPI {

    @GET("chats")
    suspend fun getChatList() : Response<List<ChatEntity>>

}