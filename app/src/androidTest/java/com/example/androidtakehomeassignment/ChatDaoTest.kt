package com.example.androidtakehomeassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidtakehomeassignment.room.ChatDatabase
import com.example.androidtakehomeassignment.room.dao.ChatDao
import com.example.androidtakehomeassignment.room.model.ChatEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChatDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var chatDB: ChatDatabase
    lateinit var chatDao: ChatDao

    @Before
    fun setup(){
        chatDB = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ChatDatabase::class.java)
            .allowMainThreadQueries().build()

        chatDao = chatDB.chatDao()
    }

    @Test
    fun insertChatList_expectedChatList() = runBlocking{
        val chatList = arrayOf(
            ChatEntity(1, "sender", "Joel", "receiver", "Jenny", "text", "Hello!", 1625490000000),
            ChatEntity(2, "sender", "Jenny", "receiver", "Joel", "text", "Hi there!", 1625490060000),
        )
        chatDao.insertChatList(chatList)

        val result = chatDao.getAllChats()

        Assert.assertEquals(2, result.size)
        Assert.assertEquals("Hello!", result[0].messageContent)

    }

    @Test
    fun insertNewChat_expectedChatList() = runBlocking{
        val chat = ChatEntity(1, "sender", "Joel", "receiver", "Jenny", "text", "Hello!", 1625490000000)
        chatDao.insertNewChat(chat)

        val result = chatDao.getAllChats()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Hello!", result[0].messageContent)

    }

    @After//after every test case
    fun teardown(){
        chatDB.close()
    }

}