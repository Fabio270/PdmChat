package com.fabioseyiji.pdmchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessengerAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<MessengerAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.container_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val senderTextView: TextView = itemView.findViewById(R.id.AuthorTv)
        private val timestampTextView: TextView = itemView.findViewById(R.id.TimeTv)
        private val messageTextView: TextView = itemView.findViewById(R.id.MessageTv)

        fun bind(message: Message) {
            senderTextView.text = message.sender
            timestampTextView.text = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(java.util.Date(message.timestamp))
            messageTextView.text = message.message
        }
    }
}