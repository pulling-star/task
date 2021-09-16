package com.example.chatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.data.Message
import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID

class MessagingAdapter:RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messageList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvMessage = itemView.findViewById<TextView>(R.id.tv_message)
        val tvBotMessage = itemView.findViewById<TextView>(R.id.tv_bot_message)
        init{
            itemView.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tvBotMessage.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tvMessage.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
    }
}