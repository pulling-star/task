package com.example.itunesapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.R
import com.example.itunesapp.model.ResultModel
import com.example.itunesapp.viewModel.SearchViewModel


class MainActivity : AppCompatActivity() {
    lateinit var main_search_editText: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        bindViews()
        searchViewModel.liveDataSearch.observe(this, Observer {
            setRecycleViewList(it)
        })
        main_search_editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.callSearchList(this@MainActivity, s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun bindViews() {
        main_search_editText = findViewById(R.id.main_search_editText)
        recyclerView = findViewById(R.id.recyclerViewSearch)
    }

    private fun setRecycleViewList(userList: List<ResultModel>) {
        val mAdapter = SearchAdapter(this@MainActivity, userList)
        val mLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }
}