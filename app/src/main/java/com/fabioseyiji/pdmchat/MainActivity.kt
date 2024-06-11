package com.fabioseyiji.pdmchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fabioseyiji.pdmchat.databinding.ActivityMainBinding
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var db: DatabaseReference
    private val messages = mutableListOf<Message>()
    private lateinit var adapter: MessengerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        db = FirebaseDatabase.getInstance().reference.child("messages")

        adapter = MessengerAdapter(messages)
        amb.MessagesRv.layoutManager = LinearLayoutManager(this)
        amb.MessagesRv.adapter = adapter

        amb.SendBt.setOnClickListener {
            startActivity(Intent(this, SendActivity::class.java))
        }

        displayMessages()
    }

    private fun displayMessages() {
        db.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                if (message != null) {
                    messages.add(message)
                    adapter.notifyItemInserted(messages.size - 1)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}