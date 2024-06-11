package com.fabioseyiji.pdmchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fabioseyiji.pdmchat.databinding.ActivitySendBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SendActivity : AppCompatActivity() {
    private val asb: ActivitySendBinding by lazy {
        ActivitySendBinding.inflate(layoutInflater)
    }

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asb.root)

        db = FirebaseDatabase.getInstance().reference.child("messages")

        asb.SendSubmitBt.setOnClickListener {
            val author = asb.AuthorEt.text.toString()
            val message = asb.messageEt.text.toString()
            val time = System.currentTimeMillis()

            if(message.isEmpty()){
                Toast.makeText(this, "Message cannot be empty!", Toast.LENGTH_SHORT).show()
            }
            else if (message.length > 150){
                Toast.makeText(this, "Message cannot be bigger than 150 char!", Toast.LENGTH_SHORT).show()
            }
            else{
                val newMessage = Message(author, time, message)
                db.push().setValue(newMessage)
                finish()
            }
        }
    }
}