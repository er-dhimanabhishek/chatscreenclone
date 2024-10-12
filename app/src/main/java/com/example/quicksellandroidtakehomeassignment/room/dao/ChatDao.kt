package com.example.quicksellandroidtakehomeassignment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatList(chat: Array<ChatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewChat(chat: ChatEntity)

    @Query("SELECT * FROM chatdatacluster ORDER BY timestamp ASC")
    fun getAllChats(): List<ChatEntity>

}