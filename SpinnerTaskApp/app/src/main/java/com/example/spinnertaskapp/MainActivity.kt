package com.example.spinnertaskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    lateinit var countrySpinner: Spinner
    lateinit var stateSpinner: Spinner
    lateinit var districtSpinner: Spinner

    var countries = ArrayList<String>()
    var States = ArrayList<String>()
    var districts = ArrayList<String>()


    var indStates:MutableList<String> = mutableListOf()
    var usaStates:MutableList<String> = mutableListOf()
    var chinaStates:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countrySpinner= findViewById(R.id.countrySpinner)
        stateSpinner= findViewById(R.id.stateSpinner)
        districtSpinner= findViewById(R.id.districtSpinner)

        val s = "~IN*TN>CHENNAI>MADURAI>KOVAI>ERODE*AP>ONGOLE>TENALI>VIZAG*TS>HYDERABAD>WARANGAL>VIKARABAD~USA*ALASKA>JUNEAU>SITKA>KENAI~CHINA*HAINAN>HAIKOU>SANYA>DONGFANG*HUNAN>CHANGHSA>YUEYANG>CHANGDE"

        val s1 = s.substring(1,s.length)
        val seperatedStringArray = s1.split("~")
        Log.d(TAG,"seperatedStringArray = ${seperatedStringArray}")

        val indStates = seperatedStringArray[0].split("\\*".toRegex())

        Log.d(TAG,"indStates = ${indStates.toString()}")

        val usaStates =  seperatedStringArray[1].split("\\*".toRegex())

        Log.d(TAG,"usaStates = ${usaStates.toString()}")

        val chinaStates = seperatedStringArray[2].split("\\*".toRegex())

        Log.d(TAG,"chinaStates = ${chinaStates.toString()}")

        countries.add(indStates[0])
        countries.add(usaStates[0])
        countries.add(chinaStates[0])

        Log.d(TAG,"countries = ${countries.toString()}")
        
        setUpSpinner()
    }

    private fun setUpSpinner() {
        val countryAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            countries
        )
        countrySpinner.adapter = countryAdapter

        val stateAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            States
        )
        stateSpinner.adapter = stateAdapter

        val districtAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            districts
        )
        districtSpinner.adapter = districtAdapter

        countrySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }


}