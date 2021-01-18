package com.example.sportsapp.sportsteam

import android.os.Bundle
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
import com.example.sportsapp.model.TeamModel

class TeamFragment : Fragment() {
    lateinit var teamRecyclerView: RecyclerView

    lateinit var teamFragmentViewModel: TeamFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teamFragmentViewModel = ViewModelProvider(this).get(TeamFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_team, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamRecyclerView = view.findViewById(R.id.teamRecyclerViewList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamFragmentViewModel.CallTeamList()

        teamFragmentViewModel.liveDataTeamSearch.observe(viewLifecycleOwner, Observer{
                setRecyclerView(it)
        })
    }

    private fun setRecyclerView(list: List<TeamModel>) {
        val mAdapter = TeamAdapter(this.requireContext(), list)
        val mLayoutManager = GridLayoutManager(this.requireContext(), 2)
        teamRecyclerView.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        teamRecyclerView.itemAnimator = DefaultItemAnimator()
        teamRecyclerView.adapter = mAdapter

    }

}