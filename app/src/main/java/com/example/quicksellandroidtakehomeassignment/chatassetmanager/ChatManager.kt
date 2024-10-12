package com.example.quicksellandroidtakehomeassignment.chatassetmanager

import android.content.Context
import android.content.SharedPreferences
import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity
import com.google.gson.Gson

class ChatManager {
    fun loadChatFromAssets(context: Context, filename: String): Array<ChatEntity> {
        val inputStream = context.assets.open(filename)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<ChatEntity>::class.java)
    }
}