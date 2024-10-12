package com.example.androidtakehomeassignment.chatviewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtakehomeassignment.ChatConstants
import com.example.androidtakehomeassignment.chatassetmanager.ChatManager
import com.example.androidtakehomeassignment.model.Result
import com.example.androidtakehomeassignment.repository.ChatRepository
import com.example.androidtakehomeassignment.room.model.ChatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val sharedPreferences: SharedPreferences,
private val chatRepository: ChatRepository) : ViewModel() {

    val KEY_FIRST_TIME_CHAT_LOAD = "FIRST_TIME_CHAT_LOAD"

    private val sendMsgMutableLiveData = MutableLiveData<Boolean>()

    var currentText: String = ""

    val sendMsgDataState: LiveData<Boolean>
    get() = sendMsgMutableLiveData

    private val chatListLiveData = MutableLiveData<Result<List<ChatEntity>>>()

    val chatListObj: LiveData<Result<List<ChatEntity>>>
        get() = chatListLiveData

    fun updateSendDataState(state: Boolean, msg: String){
        sendMsgMutableLiveData.postValue(state)
        currentText = msg
    }

    fun loadChatFromAsset(context: Context): Array<ChatEntity>{
        val manager = ChatManager()
        val chat = manager.loadChatFromAssets(context, ChatConstants.CHAT_FILE_NAME)
        sharedPreferences.edit().putBoolean(KEY_FIRST_TIME_CHAT_LOAD, true).apply()
        return chat
    }

    fun insertDefaultChatListToDB(chat: Array<ChatEntity>){
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.insertDefaultChat(chat)
        }
    }

    fun getChatFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            chatListLiveData.postValue(Result.Loading())
            delay(400)
            val result = chatRepository.getChatFromDB()
            chatListLiveData.postValue(Result.Success(result))
        }
    }

    fun insertNewMessageToDB(timestamp: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val newMsg = ChatEntity(  id = 0L,"sender", "John Doe",
                "receiver", "Jane Smith", "text", currentText, timestamp)
            currentText = ""
            chatRepository.insertNewChat(newMsg)
        }
    }

}