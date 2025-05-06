package com.example.uts_pemrogramanmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ChatAdapter(context: Context, private val chatItems: List<ChatItem>) :
    ArrayAdapter<ChatItem>(context, R.layout.item_chat, chatItems) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_chat, parent, false)

        val chatItem = chatItems[position]

        val profilePic = view.findViewById<ImageView>(R.id.ivProfilePic)
        val name = view.findViewById<TextView>(R.id.tvName)
        val lastMessage = view.findViewById<TextView>(R.id.tvLastMessage)
        val time = view.findViewById<TextView>(R.id.tvTime)

        profilePic.setImageResource(chatItem.profilePicture)
        name.text = chatItem.name
        lastMessage.text = chatItem.lastMessage
        time.text = chatItem.time

        return view
    }
}