package com.example.quicksellandroidtakehomeassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicksellandroidtakehomeassignment.ChatConstants
import com.example.quicksellandroidtakehomeassignment.R
import com.example.quicksellandroidtakehomeassignment.model.ChatItem


class ChatAdapter : ListAdapter<ChatItem, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    companion object {
        private const val VIEW_TYPE_TEXT_SENDER = 1
        private const val VIEW_TYPE_TEXT_RECEIVER = 2
        private const val VIEW_TYPE_AUDIO_SENDER = 3
        private const val VIEW_TYPE_AUDIO_RECEIVER = 4
        private const val VIEW_TYPE_VIDEO_SENDER = 5
        private const val VIEW_TYPE_VIDEO_RECEIVER = 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (val chatItem = getItem(position)) {
            is ChatItem.TextMessage -> if (chatItem.senderId == ChatConstants.SENDER_ID) VIEW_TYPE_TEXT_SENDER else VIEW_TYPE_TEXT_RECEIVER
            is ChatItem.AudioMessage -> if (chatItem.senderId == ChatConstants.SENDER_ID) VIEW_TYPE_AUDIO_SENDER else VIEW_TYPE_AUDIO_RECEIVER
            is ChatItem.VideoMessage -> if (chatItem.senderId == ChatConstants.SENDER_ID) VIEW_TYPE_VIDEO_SENDER else VIEW_TYPE_VIDEO_RECEIVER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TEXT_SENDER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_outgoing_text_message, parent, false)
                TextMessageSenderViewHolder(view)
            }
            VIEW_TYPE_TEXT_RECEIVER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incoming_text_message, parent, false)
                TextMessageReceiverViewHolder(view)
            }
            // Add cases for audio and video sender/receiver
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val chatItem = getItem(position)) {
            is ChatItem.TextMessage -> if (chatItem.senderId == ChatConstants.SENDER_ID) {
                (holder as TextMessageSenderViewHolder).bind(chatItem)
            } else {
                (holder as TextMessageReceiverViewHolder).bind(chatItem)
            }
            // Add cases for AudioMessage and VideoMessage
            else -> {

            }
        }
        setFadeAnimation(holder.itemView)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }

    // ViewHolder for Text Messages
    class TextMessageSenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ChatItem.TextMessage) {
            itemView.findViewById<TextView>(R.id.tvOutgoingMessageText).text = item.message
            // Bind other views like timestamp, etc.
        }
    }

    class TextMessageReceiverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ChatItem.TextMessage) {
            itemView.findViewById<TextView>(R.id.tvIncomingMessageText).text = item.message
            // Bind other views like timestamp, etc.
        }
    }

    // ViewHolder for Audio Messages
    class AudioMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ChatItem.AudioMessage) {
            // Bind audio file, duration, etc.
        }
    }

    // ViewHolder for Video Messages
    class VideoMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ChatItem.VideoMessage) {
            // Bind video file, thumbnail, etc.
        }
    }

    class ChatDiffCallback : DiffUtil.ItemCallback<ChatItem>() {
        override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem is ChatItem.TextMessage && newItem is ChatItem.TextMessage && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem == newItem
        }
    }
}
