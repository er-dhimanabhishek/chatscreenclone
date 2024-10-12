package com.example.quicksellandroidtakehomeassignment.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.quicksellandroidtakehomeassignment.chatviewmodel.ChatViewModel
import com.example.quicksellandroidtakehomeassignment.getOrAwaitValue
import com.example.quicksellandroidtakehomeassignment.repository.ChatRepository
import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ChatViewModelTest {

    @Mock
    lateinit var chatRepository: ChatRepository

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Mock
    lateinit var context: Context

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    /*@Test
    fun test_getChat_expectedEmptyList() = runTest{
        Mockito.`when`(chatRepository.getChatFromDB()).thenReturn(emptyList())

        val sut = ChatViewModel(sharedPreferences, chatRepository)
        sut.getChatFromDB()

        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.chatListObj.getOrAwaitValue()

        Assert.assertEquals(0, result.chatList?.size)

    }

    @Test
    fun test_getChat_expectedChatList() = runTest{
        val chatList = listOf(
            ChatEntity(1, "sender", "Joel", "receiver", "Jenny", "text", "Hello!", 1625490000000),
            ChatEntity(2, "sender", "Jenny", "receiver", "Joel", "text", "Hi there!", 1625490060000),
        )
        Mockito.`when`(chatRepository.getChatFromDB()).thenReturn(chatList)

        val sut = ChatViewModel(sharedPreferences, chatRepository)
        sut.getChatFromDB()

        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.chatListObj.getOrAwaitValue()

        Assert.assertEquals(2, result.chatList?.size)

    }*/

}