package com.example.androidtakehomeassignment.repository

import android.util.Log
import com.example.androidtakehomeassignment.retrofitapi.ChatAPI
import com.example.androidtakehomeassignment.room.ChatDatabase
import com.example.androidtakehomeassignment.room.model.ChatEntity
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatAPI: ChatAPI,
    private val database: ChatDatabase) {

    suspend fun insertDefaultChat(chat: Array<ChatEntity>){
        Log.e("CHAT", "Add chat to DB")
        database.chatDao().insertChatList(chat)
    }

    suspend fun insertNewChat(chat: ChatEntity){
        Log.e("CHAT", "Add chat to DB")
        database.chatDao().insertNewChat(chat)
    }

    fun getChatFromDB(): List<ChatEntity> {

        return database.chatDao().getAllChats()

    }

}