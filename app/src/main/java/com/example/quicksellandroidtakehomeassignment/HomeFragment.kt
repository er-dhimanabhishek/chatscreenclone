package com.example.quicksellandroidtakehomeassignment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicksellandroidtakehomeassignment.chatviewmodel.ChatViewModel
import com.example.quicksellandroidtakehomeassignment.databinding.FragmentHomeBinding
import com.example.quicksellandroidtakehomeassignment.dependencies.SharedPrefModule
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var _binding: FragmentHomeBinding
    lateinit var chatViewModel: ChatViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //sharedPreferences = SharedPrefModule().provideSharedPreference(requireContext())

        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        if (!sharedPreferences.getBoolean(chatViewModel.KEY_FIRST_TIME_CHAT_LOAD, false)){
            val chat = chatViewModel.loadChatFromAsset(requireContext())
            chatViewModel.insertDefaultChatListToDB(chat)
        }

        _binding.btnOpenChat.setOnClickListener {
            findNavController().navigate(R.id.action_quickSellHomeFragment_to_quickSellChatFragment)
        }
    }

}