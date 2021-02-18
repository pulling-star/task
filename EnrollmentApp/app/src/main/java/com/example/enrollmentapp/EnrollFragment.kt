package com.example.enrollmentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.enrollmentapp.database.Users
import com.example.enrollmentapp.database.UsersDatabase

class EnrollFragment : Fragment() {
    lateinit var profileImage:ImageView
    lateinit var firstName:EditText
    lateinit var lastName:EditText
    lateinit var dob:EditText
    lateinit var gender:EditText
    lateinit var country:EditText
    lateinit var state:EditText
    lateinit var homeTown:EditText
    lateinit var phoneNumber:EditText
    lateinit var telephoneNum:EditText
    lateinit var submitButton:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_enroll, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileImage = view.findViewById(R.id.profileImage)
        firstName = view.findViewById(R.id.firstName)
        lastName = view.findViewById(R.id.lastName)
        dob = view.findViewById(R.id.dob)
        gender = view.findViewById(R.id.gender)
        country = view.findViewById(R.id.country)
        state = view.findViewById(R.id.state)
        homeTown = view.findViewById(R.id.homeTown)
        phoneNumber = view.findViewById(R.id.phoneNumber)
        telephoneNum = view.findViewById(R.id.telephoneNum)
        submitButton = view.findViewById(R.id.submitButton)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val userDao = UsersDatabase.getInstance(this.requireContext()).usersDao()
        val user = Users()
        submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                user.firstName = firstName.text.toString()
                user.lastName = lastName.text.toString()
                user.dob = dob.text.toString()
                user.gender = gender.text.toString()
                user.country = country.text.toString()
                user.state = country.text.toString()
                user.homeTown = country.text.toString()
                user.phnNum = phoneNumber.text.toString()
                user.telNum = telephoneNum.text.toString()
                UsersDatabase.databaseWriteExecutor.execute {
                    userDao.insertUser(user)
                    Log.d("EnrollFragment", "Inserted")
                    Log.d("EnrollFragment", "users after click = $user")
                }
                Toast.makeText(
                    this@EnrollFragment.requireContext(),
                    "${user.firstName} ${user.lastName} is added as an user", Toast.LENGTH_SHORT
                )
                    .show()
                val fr = fragmentManager?.beginTransaction()
                fr?.replace(R.id.viewPager,UsersFragment())
                fr?.commit()
            }

        })
    }

}