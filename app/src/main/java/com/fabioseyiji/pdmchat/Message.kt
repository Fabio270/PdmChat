package com.fabioseyiji.pdmchat

data class Message(
    val sender: String = "",
    val timestamp: Long = 0,
    val message: String = ""
)