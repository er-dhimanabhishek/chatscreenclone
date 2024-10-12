package com.example.quicksellandroidtakehomeassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicksellandroidtakehomeassignment.room.dao.ChatDao
import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity

@Database(entities = [ChatEntity::class], version = 1, exportSchema = false)
abstract class ChatDatabase: RoomDatabase() {

    abstract fun chatDao(): ChatDao

}