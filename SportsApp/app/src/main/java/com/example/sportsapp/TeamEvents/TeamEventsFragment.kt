package com.example.sportsapp.TeamEvents

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.sportsapp.R
import com.example.sportsapp.model.TeamModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class TeamEventsFragment : Fragment() {
    lateinit var searchText :EditText
    lateinit var teamName: TextView
    lateinit var teamDescription:TextView
    lateinit var searchTextButton:Button
    lateinit var teamEventsViewModel:TeamEventsViewModel
    private lateinit var args : TeamEventsFragmentArgs
    lateinit var scheduleFab:ExtendedFloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = TeamEventsFragmentArgs.fromBundle(requireArguments())
//        Toast.makeText(this.requireContext(),"$args.teamId",Toast.LENGTH_SHORT).show()
//        Log.d("TeamEvents","${args.teamId},${args.teamName},${args.teamDescription}")
        teamEventsViewModel = ViewModelProvider(this).get(TeamEventsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_team_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teamEventsViewModel.CallTeamDetailList(args.teamId)
        teamEventsViewModel.liveDataTeamDetailSearch.observe(viewLifecycleOwner, Observer {
            setText(it[0])
        })
        searchTextButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                teamEventsViewModel.CallTeamSearchDetailList(searchText.text.toString())
                Log.d("TeamEvents","${searchText.text.toString()}")
            }

        })
        scheduleFab.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                v?.findNavController()?.navigate(TeamEventsFragmentDirections
                        .actionTeamEventsFragmentToScheduleFragment(args.teamId))
            }

        })
    }

    private fun setText(model: TeamModel) {
        teamName.text = model.strTeam
        teamDescription.text = model.strDescriptionEN
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchText = view.findViewById(R.id.main_search_editText)
        teamName = view.findViewById(R.id.teamNameTextView)
        teamDescription = view.findViewById(R.id.teamDescriptionTextView)
        searchTextButton = view.findViewById(R.id.searchTextButton)
        scheduleFab = view.findViewById(R.id.scheduleFab)
    }
}