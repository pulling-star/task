package com.example.zomatoapp.restaurants

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.model.RestaurantModel
import com.google.android.material.snackbar.Snackbar
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


class RestaurantFragment : Fragment() {
    companion object {
        const val TAG = "RestaurantFragment"
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    lateinit var restaurantRecyclerViewList: RecyclerView
    lateinit var searchLocationSearchEditText: SearchView
    lateinit var restaurantFragmentViewModel: RestaurantFragmentViewModel
    lateinit var searchRestaurantEditText:EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restaurantFragmentViewModel = ViewModelProvider(this)
            .get(RestaurantFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantRecyclerViewList = view.findViewById(R.id.restaurantRecyclerViewList)
        searchLocationSearchEditText = view.findViewById(R.id.searchLocationSearchEditText)
        searchRestaurantEditText = view.findViewById(R.id.searchRestaurantEditText)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestLocationPermission()

        searchLocationSearchEditText.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    restaurantFragmentViewModel.callSearchLocationApi(query)
                    restaurantFragmentViewModel.liveDataLocationSearch.observe(viewLifecycleOwner,
                        Observer {
                            if (searchRestaurantEditText.text.toString() != "") {
                                restaurantFragmentViewModel.callSearchRestaurantApi(
                                    it.location_suggestions[0].entity_id.toString(),
                                    searchRestaurantEditText.text.toString()
                                )
                                restaurantFragmentViewModel.livedataRestaurantSearch.observe(
                                    viewLifecycleOwner,
                                    Observer {
                                        setRecyclerView(it.restaurants)
                                    })
                            } else {
                                view?.let { it1 ->
                                    Snackbar.make(
                                        it1,
                                        "Please enter Restaurant name",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        })

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setRecyclerView(list: List<RestaurantModel>) {
        val mAdapter = RestaurantAdapter(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        restaurantRecyclerViewList.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        restaurantRecyclerViewList.itemAnimator = DefaultItemAnimator()
        restaurantRecyclerViewList.adapter = mAdapter
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>?,
//        grantResults: IntArray?
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults!!)
//
//        // Forward results to EasyPermissions
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    fun requestLocationPermission() {
        val perms = (Manifest.permission.ACCESS_FINE_LOCATION)
        if (EasyPermissions.hasPermissions(this.requireContext(), perms)) {
//            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Please grant the location permission",
                REQUEST_LOCATION_PERMISSION,
                perms
            )
        }
    }


}