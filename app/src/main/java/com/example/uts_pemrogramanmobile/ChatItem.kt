package com.example.uts_pemrogramanmobile

data class ChatItem(
    val id: Int,
    val name: String,
    val lastMessage: String,
    val time: String,
    val profilePicture: Int = android.R.drawable.ic_menu_myplaces
)