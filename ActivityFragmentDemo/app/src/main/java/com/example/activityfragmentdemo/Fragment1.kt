package com.example.activityfragmentdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activityfragmentdemo.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    companion object{
        const val TAG = "MainActivity"
    }
    lateinit var binding:Fragment1Binding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter:Fragment1Adapter
    var list = ArrayList<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"Fragment1-onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Fragment1-onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG,"Fragment1-onCreateView")
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"Fragment1-onViewCreated")
        binding = Fragment1Binding.bind(view)
        list = arrayListOf("Lambo","Audi","BMW","MiniCooper","Honda","Hyundai","Porsche","Jaguar",
            "Citroen","Nissan","Bugatti","Mercedes","Ferrari","Toyota","LandRover")
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        adapter = Fragment1Adapter(this.requireContext(),
            this.requireActivity() as MainActivity,list)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.fg1RecyclerView.layoutManager = linearLayoutManager
        binding.fg1RecyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"Fragment1-onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Fragment1-onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Fragment1-onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Fragment1-onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Fragment1-onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Fragment1-onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"Fragment1-onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"Fragment1-onDetach")
    }
}