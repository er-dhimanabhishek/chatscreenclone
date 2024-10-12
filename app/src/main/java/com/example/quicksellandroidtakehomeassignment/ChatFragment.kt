package com.example.quicksellandroidtakehomeassignment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicksellandroidtakehomeassignment.adapter.ChatAdapter
import com.example.quicksellandroidtakehomeassignment.chatviewmodel.ChatViewModel
import com.example.quicksellandroidtakehomeassignment.databinding.FragmentChatBinding
import com.example.quicksellandroidtakehomeassignment.model.ChatItem
import com.example.quicksellandroidtakehomeassignment.room.model.ChatEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ChatFragment : Fragment() {

    lateinit var _binding: FragmentChatBinding
    lateinit var chatViewModel: ChatViewModel
    lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_chat, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        chatAdapter = ChatAdapter()

        chatViewModel.updateSendDataState(false, "")

        (activity as AppCompatActivity).setSupportActionBar(_binding.actionToolbarChatFragment)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        _binding.actionToolbarChatFragment.navigationIcon = ContextCompat.getDrawable(
            requireContext(),R.drawable.ic_back_nav)
        _binding.actionToolbarChatFragment.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        _binding.quickSellAttachFiles.setOnClickListener {
            showToast("Attach files, coming soon...")
        }

        _binding.quickSellChatAudioCall.setOnClickListener {
            showToast("Audio call, coming soon...")
        }

        _binding.quickSellChatMoreOptions.setOnClickListener {
            showToast("More options, coming soon...")
        }

        _binding.btnQuickSellCamera.setOnClickListener {
            showToast("Camera, coming soon...")
        }

        _binding.btnQuickSellEmojiFace.setOnClickListener {
            showToast("Emoji's, coming soon...")
        }

        _binding.quickSellProfilePic.setOnClickListener {
            showToast("Profile, coming soon...")
        }

        _binding.etMessageText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if ((p0?.length ?: 0) > 0){
                    chatViewModel.updateSendDataState(true, p0.toString())
                }else{
                    chatViewModel.updateSendDataState(false, "")
                }

            }

        })

        chatViewModel.sendMsgDataState.observe(viewLifecycleOwner) { state ->
            updateSendButtonState(state)
            _binding.btnQuickSellAudioAndTextMsg.setOnClickListener {
                if (state){
                    //send message
                    //Toast.makeText(requireContext(), "Message sent.", Toast.LENGTH_LONG).show()
                    chatViewModel.insertNewMessageToDB(System.currentTimeMillis())
                    chatViewModel.getChatFromDB()
                    _binding.etMessageText.text.clear()
                }else{
                    showToast("Audio message, coming soon...")
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            val chatData = withContext(Dispatchers.IO) {
                chatViewModel.getChatFromDB() // Loading data in background
            }
        }

        chatViewModel.chatListObj.observe(viewLifecycleOwner){ obj ->
            if (obj.chatList?.isEmpty() == true){
                Log.e("CHAT", "No chat in DB")
            }else{
                //Log.e("CHAT", "${obj.chatList?.get(0)?.messageContent}")
                view.post {
                    lifecycleScope.launch(Dispatchers.Default) {
                        populateRecyclerViewWithChat(obj.chatList) // Updating UI on main thread
                    }
                }
            }
        }

        _binding.rvQuickSellChatList.adapter = chatAdapter
        _binding.rvQuickSellChatList.layoutManager = LinearLayoutManager(requireContext())
        _binding.rvQuickSellChatList.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
            if (bottom < oldBottom) {
                _binding.rvQuickSellChatList.postDelayed(
                    Runnable { _binding.rvQuickSellChatList.smoothScrollToPosition(chatAdapter.itemCount) },
                    100
                )
            }
        }
    }

    private suspend fun populateRecyclerViewWithChat(chatList: List<ChatEntity>?) {
        val chatItems = chatList?.map { convertEntityToChatItem(it) }
        withContext(Dispatchers.Main){
            if (chatItems?.isNotEmpty() == true) {
                chatAdapter.submitList(chatItems)
                _binding.rvQuickSellChatList.smoothScrollToPosition(chatItems.size - 1)
            }
        }
    }

    private fun updateSendButtonState(stateSend: Boolean) {
        if (stateSend) {
            _binding.btnQuickSellAudioAndTextMsg.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_send
                )
            )
        } else {
            _binding.btnQuickSellAudioAndTextMsg.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_mic
                )
            )
        }
    }

    private fun showToast(msg: String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun convertEntityToChatItem(chatEntity: ChatEntity): ChatItem {
        return when (chatEntity.messageType) {
            "text" -> ChatItem.TextMessage(
                id = chatEntity.id.toInt(),
                senderId = chatEntity.senderId,
                message = chatEntity.messageContent,
                timestamp = chatEntity.timestamp
            )
            "audio" -> ChatItem.AudioMessage(
                id = chatEntity.id.toInt(),
                senderId = chatEntity.senderId,
                audioFilePath = chatEntity.messageContent,
                duration = extractDurationFromMessageContent(chatEntity.messageContent),
                timestamp = chatEntity.timestamp
            )
            "video" -> ChatItem.VideoMessage(
                id = chatEntity.id.toInt(),
                senderId = chatEntity.senderId,
                videoFilePath = chatEntity.messageContent,
                thumbnailPath = extractThumbnailPathFromMessageContent(chatEntity.messageContent),
                duration = extractDurationFromMessageContent(chatEntity.messageContent),
                timestamp = chatEntity.timestamp
            )
            else -> throw IllegalArgumentException("Unknown message type: ${chatEntity.messageType}")
        }
    }

    private fun extractDurationFromMessageContent(content: String): Long {
        // Extract duration from the content
        // This is a placeholder
        return 0L
    }

    private fun extractThumbnailPathFromMessageContent(content: String): String {
        // Extract thumbnail path from the content
        // This is a placeholder
        return ""
    }


}