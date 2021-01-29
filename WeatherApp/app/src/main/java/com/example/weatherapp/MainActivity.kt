package com.example.weatherapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.BaseModel
import com.example.weatherapp.model.Daily
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var dateTimeTextView:TextView
    lateinit var temperatureTextView:TextView
    lateinit var weatherNameTextView:TextView
    lateinit var humidityTextView:TextView
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var weatherForecast_list_RecyclerView:RecyclerView
    lateinit var backdropImage:ImageView
    lateinit var lineGraph:GraphView
    lateinit var lineSeries:LineGraphSeries<DataPoint>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        bindviews()
        mainActivityViewModel.CallWeatherApi()
        mainActivityViewModel.liveDataWeather.observe(this, Observer {
            dateTimeTextView.text = dateTimeFormat(it.current.dt)
            temperatureTextView.text = tempFormat(it.current.temp)
            weatherNameTextView.text = it.current.weather.get(0).main.toString()
            humidityTextView.text = it.current.humidity.toString() + "%"
            setRecyclerView(it.daily)
            setGraphVIew(it)
        })

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
        series.appendData(DataPoint(x1+1.0, y2), true, 5)
        series.appendData(DataPoint(x1+2.0, y3), true, 5)
        series.appendData(DataPoint(x1+3.0, y4), true, 5)
        series.appendData(DataPoint(x1+4.0, y5), true, 5)
        lineGraph.addSeries(series)
        lineGraph.getGridLabelRenderer().setLabelFormatter(object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    return super.formatLabel(value, isValueX)+":00"
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
        temperatureTextView=findViewById(R.id.temperatureTextView)
        weatherNameTextView=findViewById(R.id.weatherNameTextView)
        humidityTextView=findViewById(R.id.humidityTextView)
        weatherForecast_list_RecyclerView=findViewById(R.id.weatherForecast_list_RecyclerView)
        backdropImage=findViewById(R.id.backdropImage)
        lineGraph=findViewById(R.id.lineGraph)
    }
}