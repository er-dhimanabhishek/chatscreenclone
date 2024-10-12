package com.example.androidtakehomeassignment.model

sealed class ChatItem {
    data class TextMessage(
        val id: Int,
        val senderId: String,
        val message: String,
        val timestamp: Long
    ) : ChatItem()

    data class AudioMessage(
        val id: Int,
        val senderId: String,
        val audioFilePath: String,
        val duration: Long,
        val timestamp: Long
    ) : ChatItem()

    data class VideoMessage(
        val id: Int,
        val senderId: String,
        val videoFilePath: String,
        val thumbnailPath: String,
        val duration: Long,
        val timestamp: Long
    ) : ChatItem()
}