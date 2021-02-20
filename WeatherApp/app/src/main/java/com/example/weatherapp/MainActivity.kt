package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.Daily
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnCompleteListener
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import okio.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    lateinit var dateTimeTextView: TextView
    lateinit var temperatureTextView: TextView
    lateinit var weatherNameTextView: TextView
    lateinit var humidityTextView: TextView
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var weatherForecast_list_RecyclerView: RecyclerView
    lateinit var backdropImage: ImageView
    lateinit var lineGraph: GraphView
    private lateinit var client: FusedLocationProviderClient
    lateinit var geocoder: Geocoder
    lateinit var lat: String
    lateinit var lon: String
    lateinit var location: EditText
    lateinit var weatherLoc: TextView
    lateinit var currentWeather: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        client = LocationServices.getFusedLocationProviderClient(this)
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        bindviews()
        requestLocationPermission()
        mainActivityViewModel.liveDataWeather.observe(this, Observer {
            dateTimeTextView.text = dateTimeFormat(it.current.dt)
            temperatureTextView.text = tempFormat(it.current.temp)
            weatherNameTextView.text = it.current.weather.get(0).main.toString()
            humidityTextView.text = it.current.humidity.toString() + "%"
            setRecyclerView(it.daily)
            setGraphVIew(it)
        })
        location.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! > 2) {
                    weatherLoc.text = formatLoc(s.toString())
                    mainActivityViewModel.CallWeatherApiLoc(s.toString())
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        mainActivityViewModel.liveDataWeatherLoc.observe(this, Observer {
            temperatureTextView.text = tempFormat(it.main.temp)
            weatherNameTextView.text = it.weather.get(0).main
            humidityTextView.text = it.main.humidity.toString() + "%"
        })
        currentWeather.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    client.lastLocation.addOnCompleteListener(object :
                        OnCompleteListener<Location> {
                        override fun onComplete(task: com.google.android.gms.tasks.Task<Location>) {
                            val location = task.result
                            if (location != null) {
                                try {
                                    val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                                    val address = geocoder.getFromLocation(
                                        location.latitude, location.longitude, 1
                                    )
                                    lat = address[0].latitude.toString()
                                    lon = address[0].longitude.toString()
                                    Log.d(TAG, "lat=$lat,lon=$lon")
                                    weatherLoc.text = formatLoc(address[0].locality)
                                    mainActivityViewModel.CallWeatherApi(lat, lon)

                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }

                        }

                    })
                }
            }

        })

    }

    private fun formatLoc(loc: String): CharSequence? {
        return "Weather Location: $loc"
    }

    @SuppressLint("NewApi")
    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            client.lastLocation.addOnCompleteListener(object : OnCompleteListener<Location> {
                override fun onComplete(task: com.google.android.gms.tasks.Task<Location>) {
                    val location = task.result
                    if (location != null) {
                        try {
                            val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                            val address = geocoder.getFromLocation(
                                location.latitude, location.longitude, 1
                            )
                            lat = address[0].latitude.toString()
                            lon = address[0].longitude.toString()
                            Log.d(TAG, "lat=$lat,lon=$lon")
                            weatherLoc.text = formatLoc(address[0].locality)
                            mainActivityViewModel.CallWeatherApi(lat, lon)

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                }

            })
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    client.lastLocation.addOnCompleteListener(object :
                        OnCompleteListener<Location> {
                        override fun onComplete(task: com.google.android.gms.tasks.Task<Location>) {
                            val location = task.result
                            if (location != null) {
                                try {
                                    val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                                    val address = geocoder.getFromLocation(
                                        location.latitude, location.longitude, 1
                                    )
                                    lat = address[0].latitude.toString()
                                    lon = address[0].longitude.toString()
                                    weatherLoc.text = formatLoc(address[0].locality)
                                    mainActivityViewModel.CallWeatherApi(lat, lon)

                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }

                        }

                    })
                }
                return
            }
            else -> {

            }
        }
    }

    private fun tempFormat(temp: Double): String {
        val temp1 = temp - 273
        val tempString = String.format("%.0f", temp1) + "°C"
        return tempString
    }

    private fun dateTimeFormat(dt: Long): String {
        val date = Date(dt * 1000L)
        val dateFormat = SimpleDateFormat("EEEE ,HH:mm")
        val dateString = dateFormat.format(date)
        return dateString
    }

    private fun setGraphVIew(it: BaseModel) {
        val unixSeconds1 = it.hourly[1].dt
        val x1 = graphformatXaxis(unixSeconds1)
        val temp1 = it.hourly[0].temp - 273
        val y1 = GraphFormat(temp1)
        val temp2 = it.hourly[1].temp - 273
        val y2 = GraphFormat(temp2)
        val temp3 = it.hourly[2].temp - 273
        val y3 = GraphFormat(temp3)
        val temp4 = it.hourly[3].temp - 273
        val y4 = GraphFormat(temp4)
        val temp5 = it.hourly[4].temp - 273
        val y5 = GraphFormat(temp5)

        var series = LineGraphSeries<DataPoint>()
        series.appendData(DataPoint(x1, y1), true, 5)
        series.appendData(DataPoint(x1 + 1.0, y2), true, 5)
        series.appendData(DataPoint(x1 + 2.0, y3), true, 5)
        series.appendData(DataPoint(x1 + 3.0, y4), true, 5)
        series.appendData(DataPoint(x1 + 4.0, y5), true, 5)
        lineGraph.addSeries(series)
        lineGraph.getGridLabelRenderer().setLabelFormatter(object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    return super.formatLabel(value, isValueX) + ":00"
                }
                return super.formatLabel(value, isValueX)
            }
        })
        lineGraph.gridLabelRenderer.setVerticalAxisTitle("Temp in °C")
    }


    private fun graphformatXaxis(unixSeconds: Long): Double {
        val date = Date(unixSeconds * 1000L)
        val dateFormat = SimpleDateFormat("HH")
        val dateString = dateFormat.format(date)
        return dateString.toDouble()
    }

    private fun GraphFormat(temp1: Double): Double {
        val temp1String = String.format("%.0f", temp1)
        return temp1String.toDouble()
    }

    private fun setRecyclerView(list: List<Daily>) {
        val mAdapter = WeatherAdapter(this, list)
        val mLayoutManager = LinearLayoutManager(this)
        weatherForecast_list_RecyclerView.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        weatherForecast_list_RecyclerView.itemAnimator = DefaultItemAnimator()
        weatherForecast_list_RecyclerView.adapter = mAdapter
    }


    private fun bindviews() {
        dateTimeTextView = findViewById(R.id.dateTimeTextView)
        temperatureTextView = findViewById(R.id.temperatureTextView)
        weatherNameTextView = findViewById(R.id.weatherNameTextView)
        humidityTextView = findViewById(R.id.humidityTextView)
        weatherForecast_list_RecyclerView = findViewById(R.id.weatherForecast_list_RecyclerView)
        backdropImage = findViewById(R.id.backdropImage)
        lineGraph = findViewById(R.id.lineGraph)
        location = findViewById(R.id.locationEditText)
        weatherLoc = findViewById(R.id.weatherLocTextView)
        currentWeather = findViewById(R.id.currentWeatherTextView)
    }
}