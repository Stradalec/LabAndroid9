package com.example.labandroid9

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        var adapter: ForecastAdapter = ForecastAdapter(ForecastDiffCallback())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(OpenWeatherMapService::class.java)

        val apiKey = resources.getString(R.string.key)
        val city = "Шклов"
        val units = "metric"

        val call = service.getForecast(city, units, apiKey)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        call.enqueue(object : Callback<Forecast> {
            override fun onResponse(call: retrofit2.Call<Forecast>, response: Response<Forecast>) {
                if (response.isSuccessful) {
                    val forecast = response.body()
                    adapter.submitList(forecast!!.list)
                }
                else
                {

                }
            }

            override fun onFailure(call: retrofit2.Call<Forecast>, t: Throwable) {
            }
        })
    }
}

interface OpenWeatherMapService {
    @GET("forecast")
    fun getForecast(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<Forecast>
}