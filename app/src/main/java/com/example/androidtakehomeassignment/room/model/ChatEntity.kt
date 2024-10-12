package com.example.androidtakehomeassignment.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatdatacluster")
data class ChatEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val senderId: String,
    val senderName: String,
    val receiverId: String,
    val receiverName: String,
    val messageType: String, // "text", "audio", "video"
    val messageContent: String, // Store text, audio path, or video path
    val timestamp: Long)