package com.example.androidtakehomeassignment

import androidx.test.core.app.ApplicationProvider
import com.example.androidtakehomeassignment.chatassetmanager.ChatManager
import com.google.gson.JsonSyntaxException
import org.junit.Assert
import org.junit.Test
import java.io.FileNotFoundException

class ChatManagerTest {
    @Test(expected = FileNotFoundException::class)
    fun populateChatFromAssets() {
        val chatManager = ChatManager()
        chatManager.loadChatFromAssets(ApplicationProvider.getApplicationContext(), "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun testPopulateChatFromAssets() {
        val chatManager = ChatManager()
        chatManager.loadChatFromAssets(ApplicationProvider.getApplicationContext(), "malformedchat.json")
    }

    @Test
    fun testPopulateChatFromAssets_validJson_expectedCount() {
        val chatManager = ChatManager()
        val list = chatManager.loadChatFromAssets(ApplicationProvider.getApplicationContext(), "chat.json")
        Assert.assertEquals(4, list.size)
    }
}