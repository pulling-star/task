package com.example.chatbot.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.data.Message
import com.example.chatbot.utils.BotResponse
import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_SEARCH
import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID
import com.example.chatbot.utils.Time
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:MessagingAdapter
    private val botList = listOf("Peter","Siri","Katana","IBot")
    private lateinit var rvMessages: RecyclerView
    private lateinit var btnSend:AppCompatButton
    private lateinit var etMessage :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = Html.fromHtml("<font color=\"red\">" + getString(R.string.app_name) + "</font>")
        rvMessages = findViewById(R.id.rv_messages)
        btnSend = findViewById(R.id.btn_send)
        etMessage = findViewById(R.id.et_message)

        recyclerView()

        clickEvents()

        val random = (0..2).random()
        customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rvMessages.adapter = adapter
        rvMessages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val time = Time.timestamp()
                adapter.insertMessage(Message(message,RECEIVE_ID,time))

                rvMessages.scrollToPosition(adapter.itemCount -1)
            }
        }
    }

    private fun clickEvents() {

        btnSend.setOnClickListener {
            sendMessage()
        }

        etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rvMessages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun sendMessage() {
        val message = etMessage.text.toString()
        val timeStamp = Time.timestamp()

        if (message.isNotEmpty()) {
            
            etMessage.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timestamp()

        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                rvMessages.scrollToPosition(adapter.itemCount - 1)

                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}