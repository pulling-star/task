package com.example.contentlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contentlistapp.adapters.ContentListAdapter
import com.example.contentlistapp.databinding.ActivityMainBinding
import com.example.contentlistapp.models.Content
import com.example.contentlistapp.viewmodels.ContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var contentAdapter : ContentListAdapter

    lateinit var gridLayoutManager: GridLayoutManager

    private val contentViewModel : ContentViewModel by viewModels() //ContentViewModel injected using Hilt

    private var contentList : MutableList<Content> = mutableListOf() //For storing all datas

    private var filteredList : MutableList<Content> = mutableListOf() //For filtered datas

    private var pageCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpItemCountOnOrientation()
        setData(pageCount)
        initListeners()
    }

    private fun setUpItemCountOnOrientation() {
        //Setting item count for portrait and landscape
        val gridCount = resources.getInteger(R.integer.grid_column_count)
        gridLayoutManager = GridLayoutManager(this,gridCount)
        binding.rvContentList.layoutManager = gridLayoutManager
    }

    //get and set data for recyclerview
    private fun setData(pageCount: Int) {
        val listData = contentViewModel.getPageData(pageCount)
        for(item in listData){
            contentList.add(item)
        }
        contentAdapter = ContentListAdapter(contentList)
        binding.rvContentList.adapter = contentAdapter
    }

    private fun initListeners() {
        //Handling recyclerview scroll
        binding.rvContentList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == contentList.size - 1){
                    if(pageCount < 3 ){
                        pageCount++
                        loadMore(pageCount)
                    }
                }
            }
        })
    }

    //loading more data based on page count
    private fun loadMore(pageNo:Int) {
        val nList = contentViewModel.getPageData(pageNo)
        contentList.addAll(nList)
        contentAdapter.setList(contentList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val searchViewItem = menu?.findItem(R.id.appSearchBar)
        val searchView = searchViewItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{ // Handling Search queries
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchText ->
                    if(searchText.length > 2 ){
                        val dummyList = contentList.filter { it.name.contains(searchText,ignoreCase = true) }
                        filteredList.clear()
                        for(item in dummyList){
                            filteredList.add(item)
                        }
                        if(filteredList.isEmpty()){
                            Toast.makeText(this@MainActivity, "Try searching for something else.", Toast.LENGTH_SHORT).show()
                        }
                        contentAdapter.setList(filteredList)
                        contentAdapter.notifyDataSetChanged()
                    } else if (searchText.isEmpty()){ // showing all datas when search is empty
                        contentAdapter.setList(contentList)
                        contentAdapter.notifyDataSetChanged()
                    }
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.appSearchBar -> {
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}