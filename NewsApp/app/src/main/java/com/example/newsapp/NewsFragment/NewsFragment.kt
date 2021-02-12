package com.example.newsapp.NewsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.Articles


class NewsFragment : Fragment() {
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsRecyclerView:RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var newsAdapter:NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = RecyclerView.VERTICAL
        newsViewModel.getNewsApi()
        newsViewModel.liveDataNews.observe(viewLifecycleOwner, Observer {
            setRecyclerView(it.articles)
        })
    }

    private fun setRecyclerView(list: List<Articles>) {
        newsAdapter = NewsAdapter(this.requireContext(),list)
        newsRecyclerView.layoutManager = layoutManager
        newsRecyclerView.adapter = newsAdapter
    }
}