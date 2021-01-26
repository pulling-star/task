package com.example.sportsapp.schedule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsapp.R
import com.example.sportsapp.model.ScheduleModel
import com.example.sportsapp.model.TeamModel
import com.example.sportsapp.sportsteam.TeamAdapter

class ScheduleFragment : Fragment() {
    companion object {
        const val TAG = "ScheduleFragment"
    }
    lateinit var args:ScheduleFragmentArgs
    lateinit var scheduleFragmentViewModel: ScheduleFragmentViewModel
    lateinit var scheduleRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = ScheduleFragmentArgs.fromBundle(requireArguments())
        Log.d(TAG,"teamId = ${args.teamId}")
        scheduleFragmentViewModel = ViewModelProvider(this).get(ScheduleFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleRecyclerView = view.findViewById(R.id.scheduleRecyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        scheduleFragmentViewModel.CallTeamEventList(args.teamId)
        scheduleFragmentViewModel.liveDataTeamSchedule.observe(viewLifecycleOwner, Observer {
            setRecyclerView(it)
        })
    }

    private fun setRecyclerView(list: List<ScheduleModel>) {
        val mAdapter = ScheduleAdapter(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        scheduleRecyclerView.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        scheduleRecyclerView.itemAnimator = DefaultItemAnimator()
        scheduleRecyclerView.adapter = mAdapter

    }


}