package com.example.androidtakehomeassignment

import android.content.Context
import android.content.res.AssetManager
import com.example.androidtakehomeassignment.chatassetmanager.ChatManager
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ChatAssetManagerTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetManager: AssetManager

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testAssetChatManager(){
        val testStream = ChatManager::class.java.getResourceAsStream("/chat.json")
        doReturn(assetManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyString())).thenReturn(testStream)

        val sut = ChatManager()
        val chat = sut.loadChatFromAssets(context, "")
        assertEquals("Hey, how's it going?", chat.get(0).messageContent)
    }


}