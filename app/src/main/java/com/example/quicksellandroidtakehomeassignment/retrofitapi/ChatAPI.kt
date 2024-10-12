package com.example.quicksellandroidtakehomeassignment.retrofitapi

import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity
import retrofit2.Response
import retrofit2.http.GET

interface ChatAPI {

    @GET("chats")
    suspend fun getChatList() : Response<List<ChatEntity>>

}