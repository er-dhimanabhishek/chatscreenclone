package com.example.quicksellandroidtakehomeassignment.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChatItemTypeConverter {

    @TypeConverter
    fun fromChatItem(item: ChatItem): String {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun toChatItem(value: String): ChatItem {
        val type = object : TypeToken<ChatItem>() {}.type
        return Gson().fromJson(value, type)
    }
}