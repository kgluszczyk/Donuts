package com.krzysztofgluczyk.malapaczkarnia

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.krzysztofgluczyk.malapaczkarnia.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).run {
            setContentView(root)
            paczek.setOnClickListener {
                zamowienie.text.toString().let { zamowienie ->
                    Intent(this@MainActivity, OrderActivity::class.java).apply {
                        putExtra(OrderActivity.ZAMOWIENIE_KEY, zamowienie)
                    }.run {
                        this@MainActivity.startActivity(this)
                    }
                }
            }
        }
        val gson = Gson()

        val httpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val donutService = retrofit.create(DonutService::class.java)

        donutService.getDonuts().enqueue(object : Callback<List<Donut>> {
            override fun onResponse(call: Call<List<Donut>>, response: Response<List<Donut>>) {
                response.body()?.forEach {
                    Log.i("DONUT", it.toString())
                }
                Toast.makeText(this@MainActivity, "Is success: ${response.isSuccessful}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Donut>>, t: Throwable) {
                Log.e("RETROFIT", "Failed to fetch donuts", t)
            }

        })
    }
}