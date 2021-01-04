package com.example.layouttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView :RecyclerView
    val dataList = ArrayList<DataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        val data1 = DataClass("YaarMe","Information Technology & Services,Chattisgarh",
            "113 followers","Building the world's most luxurious social media",
            "","","","","","firstItem")
        dataList.add(data1)
        val data2 = DataClass("YaarMe","","113 followers",
            "YaarMe is a social media platform, which aims to connect people in an organized " +
                    "manner. Users can follow accounts and optionally tag a relation with them, " +
                    "now they can switch their news feed in order to watch posts only from specific " +
                    "relation and share posts only with the people having special tag of relation.",
            "6mo","Posted by Tariq","7/11/2020","YaarMe",
            "yaarme.com","secondItem")
        dataList.add(data2)
        dataList.add(data2)
        dataList.add(data2)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val Adapter = ListAdapter(dataList)
        val linearLayoutManager =  LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.adapter= Adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }
}