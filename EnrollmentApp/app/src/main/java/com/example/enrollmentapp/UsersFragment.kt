package com.example.enrollmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enrollmentapp.database.Users
import com.example.enrollmentapp.database.UsersDatabase
import java.util.ArrayList

class UsersFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    var userList = ArrayList<Users>()
    lateinit var layoutManager: LinearLayoutManager
    lateinit var myAdapter:UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataFromDB()
        setRecyclerView()
    }


    private fun setRecyclerView() {
        myAdapter = UserAdapter(this.requireContext(),userList)
        layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = myAdapter
    }

    private fun getDataFromDB() {
        val userDao = UsersDatabase.getInstance(this.requireContext()).usersDao()
        UsersDatabase.databaseWriteExecutor.execute {
            val list = userDao.getUsers()
            for(index in list)
            {
                userList.add(index)
            }
        }
    }

}