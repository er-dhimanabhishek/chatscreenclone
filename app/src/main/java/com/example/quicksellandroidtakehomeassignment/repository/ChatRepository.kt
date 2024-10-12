package com.example.quicksellandroidtakehomeassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quicksellandroidtakehomeassignment.model.Result
import com.example.quicksellandroidtakehomeassignment.retrofitapi.ChatAPI
import com.example.quicksellandroidtakehomeassignment.room.ChatDatabase
import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity
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