package com.example.activityfragmentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activityfragmentdemo.databinding.Fragment1Binding
import com.example.activityfragmentdemo.databinding.Fragment2Binding

class Fragment2 : Fragment() {

    companion object{
        const val TAG = "MainActivity"
    }
    lateinit var binding: Fragment2Binding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter:Fragment1Adapter
    var list = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Fragment2Binding.bind(view)
        list = arrayListOf("Lambo","Audi","BMW","MiniCooper","Honda","Hyundai","Porsche","Jaguar",
            "Citroen","Nissan","Bugatti","Mercedes","Ferrari","Toyota","LandRover")
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        adapter = Fragment1Adapter(this.requireContext(),
            this.requireActivity() as MainActivity,list)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.fg2RecyclerView.layoutManager = linearLayoutManager
        binding.fg2RecyclerView.adapter = adapter
    }


}