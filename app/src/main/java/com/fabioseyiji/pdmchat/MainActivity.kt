package com.fabioseyiji.pdmchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private val messages = mutableListOf<Message>()
    private lateinit var adapter: MessengerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseDatabase.getInstance().reference.child("messages")

        adapter = MessengerAdapter(messages)
        recyclerViewMessages.layoutManager = LinearLayoutManager(this)
        recyclerViewMessages.adapter = adapter

        buttonSendMessage.setOnClickListener {
            startActivity(Intent(this, SendMessageActivity::class.java))
        }

        loadMessages()
    }
}