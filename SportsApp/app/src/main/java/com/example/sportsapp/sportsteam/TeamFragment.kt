package com.example.sportsapp.sportsteam

import android.content.Context
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
import com.example.sportsapp.model.TeamModel

class TeamFragment : Fragment() {
    companion object {
        const val TAG = "TeamFragment"
    }
    lateinit var teamRecyclerView: RecyclerView

    lateinit var teamFragmentViewModel: TeamFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateViewCalled")
        teamFragmentViewModel = ViewModelProvider(this).get(TeamFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_team, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated Called")
        teamRecyclerView = view.findViewById(R.id.teamRecyclerViewList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"onActivityCreated Called")
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttachCalled")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreateCalled")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStartCalled")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResumeCalled")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPauseCalled")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStopCalled")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyViewCalled")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroyCalled")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetachCalled")

    }
}